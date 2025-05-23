package controladores;

import java.io.IOException;

import dtos.RegistroUsuarioDto;
import servicios.RegistroServicio;
import utilidades.ContrasenaSeguraUtilidad;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registroUsuario")
public class RegistroUsuarioControlador extends HttpServlet {

    private RegistroServicio registroServicio;

    @Override
    public void init() throws ServletException {
        this.registroServicio = new RegistroServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombreUsuario");
        String telefono = request.getParameter("telefonoUsuario");
        String correo = request.getParameter("emailUsuario");
        String password = request.getParameter("passwordUsuario");
        String confirmPassword = request.getParameter("confirmPasswordUsuario");

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
            return;
        }

        // Validar seguridad de la contraseña
        if (!ContrasenaSeguraUtilidad.esSegura(password)) {
            request.setAttribute("errorMessage", "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
            return;
        }

        // Validar si el email ya existe
        if (registroServicio.emailYaRegistrado(correo)) {
            request.setAttribute("errorMessage", "El correo ya está registrado.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
            return;
        }

        // Continuar con el registro
        RegistroUsuarioDto registroDto = new RegistroUsuarioDto();
        registroDto.setNombreUsuario(nombre);
        registroDto.setTelefonoUsuario(telefono);
        registroDto.setEmailUsuario(correo);
        registroDto.setPasswordUsuario(password);

        boolean exito = registroServicio.registrarUsuario(registroDto);

        if (exito) {
            response.sendRedirect("verificacion.jsp?email=" + correo);
        } else {
            request.setAttribute("errorMessage", "No se pudo enviar el correo de verificación.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
        }
    } 
}