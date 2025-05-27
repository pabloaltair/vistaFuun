package controladores;

import dtos.RegistroUsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RegistroServicio;
import utilidades.TokenUtilidad;
import utilidades.EmailUtilidad;

import java.io.IOException;
import jakarta.mail.MessagingException;

/**
 * Servlet encargado de procesar la verificación del correo electrónico mediante token.
 * Si el token es válido, se registra definitivamente al usuario en la base de datos.
 */
@WebServlet("/verificarCuenta")
public class VerificarCuentaControlador extends HttpServlet {

    private RegistroServicio registroServicio;

    @Override
    public void init() throws ServletException {
        registroServicio = new RegistroServicio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");

        if (token == null || token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token no proporcionado.");
            return;
        }

        RegistroUsuarioDto usuarioTemporal = TokenUtilidad.validarToken(token);

        if (usuarioTemporal != null) {
            boolean creado = registroServicio.insertarUsuarioEnApi(usuarioTemporal);
            TokenUtilidad.eliminarToken(token);

            if (creado) {
                // Enviar correo de confirmación
                try {
                    EmailUtilidad emailServicio = new EmailUtilidad();
                    String asunto = "¡Cuenta verificada con éxito!";
                    String cuerpo = "Hola " + usuarioTemporal.getNombreUsuario() + ",\n\n" +
                            "Tu cuenta en *El Diario Fuun* ha sido verificada exitosamente.\n\n" +
                            "¡Gracias por unirte a nuestra comunidad!\n\n" +
                            "El equipo de El Diario Fuun.";
                    emailServicio.enviarCorreo(usuarioTemporal.getEmailUsuario(), asunto, cuerpo);
                    System.out.println("Correo de confirmación enviado a " + usuarioTemporal.getEmailUsuario());
                } catch (MessagingException e) {
                    System.out.println("Error al enviar correo de confirmación: " + e.getMessage());
                }

                response.sendRedirect("confirmado.jsp");
                System.out.println("Usuario verificado y registrado correctamente.");
            } else {
                response.sendRedirect("noconfirmado.jsp");
                System.out.println("Error al registrar usuario verificado.");
            }
        } else {
            response.sendRedirect("noconfirmado.jsp");
            System.out.println("Token inválido o no encontrado.");
        }
    }
}
