package controladores;

import java.io.IOException;

import dtos.RecuperacionContrasenaDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RecuperacionContrasenaServicio;
import servicios.ValidarEmailServicio;
import utilidades.TokenUtilidad;

@WebServlet("/enviarRecuperacion")
public class RecuperacionContrasenaControlador extends HttpServlet {

    private RecuperacionContrasenaServicio servicio;
    private ValidarEmailServicio validarEmailServicio;

    @Override
    public void init() throws ServletException {
        servicio = new RecuperacionContrasenaServicio();
        validarEmailServicio = new ValidarEmailServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("emailUsuario");

        if (email == null || email.isEmpty()) {
            String error = "Introduce un correo válido.";
            request.setAttribute("errorMessage", error);
            System.err.println("ERROR: " + error);
            request.getRequestDispatcher("recuperarContrasena.jsp").forward(request, response);
            return;
        }

        // Validar si el correo está registrado
        if (!validarEmailServicio.emailExiste(email)) {
            String error = "El correo no está registrado.";
            request.setAttribute("errorMessage", error);
            System.err.println("ERROR: " + error);
            request.getRequestDispatcher("recuperarContrasena.jsp").forward(request, response);
            return;
        }

        // Generar token y enviar enlace
        RecuperacionContrasenaDto dto = new RecuperacionContrasenaDto();
        dto.setEmailUsuario(email);
        String token = TokenUtilidad.generarYAsignarTokenRecuperacion(dto);
        boolean enviado = servicio.enviarEnlaceRecuperacion(email, token);

        if (enviado) {
            String mensaje = "Se ha enviado un enlace de recuperación al correo: " + email;
            request.setAttribute("successMessage", mensaje);
            System.out.println("ÉXITO: " + mensaje);
        } else {
            String error = "Error al enviar el correo de recuperación.";
            request.setAttribute("errorMessage", error);
            System.err.println("ERROR: " + error);
        }

        // Redirigir a la misma página para mostrar mensaje
        request.getRequestDispatcher("recuperarContrasena.jsp").forward(request, response);
    }
}
