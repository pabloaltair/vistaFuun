package controladores;

import java.io.IOException;

import dtos.RecuperacionContrasenaDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RecuperacionContrasenaServicio;
import utilidades.TokenUtilidad;
import utilidades.ContrasenaSeguraUtilidad;

@WebServlet("/procesarCambioContrasena")
public class RestablecerContrasenaControlador extends HttpServlet {

    private RecuperacionContrasenaServicio servicio;

    @Override
    public void init() throws ServletException {
        servicio = new RecuperacionContrasenaServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");
        String nuevaPassword = request.getParameter("nuevaPassword");
        String confirmar = request.getParameter("confirmarPassword");

        if (!nuevaPassword.equals(confirmar)) {
            System.out.println("[ERROR] Las contraseñas no coinciden para el token: " + token);
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("restablecerContrasena.jsp?token=" + token).forward(request, response);
            return;
        }

        // Verificar si la contraseña cumple la política de seguridad
        if (!ContrasenaSeguraUtilidad.esSegura(nuevaPassword)) {
            System.out.println("[ERROR] La contraseña no cumple con la política de seguridad para el token: " + token);
            request.setAttribute("errorMessage", "La contraseña debe tener al menos 8 caracteres, incluir una mayúscula, una minúscula, un número y un símbolo especial.");
            request.getRequestDispatcher("restablecerContrasena.jsp?token=" + token).forward(request, response);
            return;
        }

        RecuperacionContrasenaDto dto = TokenUtilidad.validarTokenRecuperacion(token);
        if (dto == null) {
            System.out.println("[ERROR] Token inválido o expirado: " + token);
            request.setAttribute("errorMessage", "Token inválido o expirado.");
            request.getRequestDispatcher("restablecerContrasena.jsp").forward(request, response);
            return;
        }

        // Validación para que la nueva contraseña no sea igual a la actual
        boolean esIgual = servicio.esContrasenaIgualActual(dto.getEmailUsuario(), nuevaPassword);
        if (esIgual) {
            System.out.println("[ERROR] La nueva contraseña es igual a la actual para el usuario: " + dto.getEmailUsuario());
            request.setAttribute("errorMessage", "La nueva contraseña no puede ser igual a la contraseña actual. Por favor, cambia la contraseña por una nueva.");
            request.getRequestDispatcher("restablecerContrasena.jsp?token=" + token).forward(request, response);
            return;
        }

        dto.setNuevaPassword(nuevaPassword);
        boolean actualizado = servicio.actualizarContrasena(dto);

        if (actualizado) {
            TokenUtilidad.eliminarTokenRecuperacion(token);
            System.out.println("[INFO] Contraseña actualizada correctamente para el usuario: " + dto.getEmailUsuario());

            // Enviar mensaje y redirección controlada por JSP
            request.setAttribute("mensaje", "¡Contraseña restablecida exitosamente!");
            request.setAttribute("tiempoEspera", 10); // segundos
            request.setAttribute("urlRedireccion", "login.jsp"); // Cambia a la ruta real de tu login

            request.getRequestDispatcher("mensajeConRedireccion.jsp").forward(request, response);
        } else {
            System.out.println("[ERROR] No se pudo actualizar la contraseña para el usuario: " + dto.getEmailUsuario());
            request.setAttribute("errorMessage", "No se pudo actualizar la contraseña.");
            request.getRequestDispatcher("restablecerContrasena.jsp?token=" + token).forward(request, response);
        }
    }
}
