package controladores;

import java.io.IOException;

import Servicios.AutentificacionServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador para manejar el inicio de sesión de un club.
 * <p>
 * Este servlet procesa solicitudes HTTP POST para autenticar a un club
 * utilizando un servicio específico de autenticación.
 * </p>
 */
@WebServlet("/loginClub")
public class LoginClubControlador extends HttpServlet {

    /**
     * Servicio encargado de manejar la lógica de autenticación del club.
     */
    private AutentificacionServicio servicio;

    /**
     * Inicializa el controlador configurando el servicio de autenticación.
     * 
     * @throws ServletException si ocurre un error en la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        this.servicio = new AutentificacionServicio();
    }

    /**
     * Procesa las solicitudes HTTP POST para autenticar un club.
     * <p>
     * Este método recoge el correo electrónico y la contraseña enviados desde el
     * formulario de inicio de sesión, verifica las credenciales utilizando el
     * servicio correspondiente, y redirige al club a su panel principal si la
     * autenticación es exitosa.
     * </p>
     * 
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud
     *                 del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta
     *                 para el cliente.
     * @throws ServletException si ocurre un error relacionado con el servlet.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger los parámetros del formulario
        String correo = request.getParameter("emailClub");
        String password = request.getParameter("passwordClub");

        // Imprimir los valores para depuración
        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + password);

        // Llamar al servicio para verificar el club
        boolean isValidClub = servicio.verificarClub(correo, password);
        System.out.println("isValidClub: " + isValidClub);

        if (isValidClub) {
            // Lógica para manejar el rol y redirigir
            String rol = servicio.getRol(); // Obtener el rol si está definido
            System.out.println("Rol del club: " + rol);
            if ("club".equals(rol)) {
                // Redirigir al panel principal del club
                response.sendRedirect("index.jsp");
            } else {
                // Rol desconocido
                request.setAttribute("errorMessage", "Rol desconocido.");
                request.getRequestDispatcher("iniciarSesionClub.jsp").forward(request, response);
            }
        } else {
            // Enviar un mensaje de error
            request.setAttribute("errorMessage", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("iniciarSesionClub.jsp").forward(request, response); // Redirigir de vuelta al formulario
        }
    }
}
