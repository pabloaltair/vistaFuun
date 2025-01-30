package controladores;

import java.io.IOException;

import Servicios.AutentificacionServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador para manejar el inicio de sesión de un usuario.
 * <p>
 * Este servlet procesa solicitudes HTTP POST para autenticar a un usuario,
 * verificar sus credenciales y redirigirlo a la página correspondiente según su rol.
 * </p>
 */
@WebServlet("/loginUsuario")
public class LoginUsuarioControlador extends HttpServlet {

    /**
     * Servicio encargado de manejar la lógica de autenticación del usuario.
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
     * Procesa las solicitudes HTTP POST para autenticar un usuario.
     * <p>
     * Este método recoge el correo electrónico y la contraseña enviados desde el
     * formulario de inicio de sesión, verifica las credenciales utilizando el
     * servicio correspondiente, y redirige al usuario a su panel principal según
     * su rol si la autenticación es exitosa.
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
        String correo = request.getParameter("email");
        String password = request.getParameter("password");

        // Imprimir los valores para depuración
        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + password);

        // Llamar al servicio para verificar el usuario
        boolean isValidUser = servicio.verificarUsuario(correo, password);

        System.out.println("isValidUser: " + isValidUser);

        if (isValidUser) {
            // Lógica para manejar el rol y redirigir
            String rol = servicio.getRol(); // Obtener el rol del usuario
            System.out.println("Rol del usuario: " + rol);
            if ("admin".equals(rol)) {
                // Redirigir al panel de administración
                response.sendRedirect("menuAdministrador.jsp");
            } else if ("usuario".equals(rol)) {
                // Redirigir al panel de usuario
                response.sendRedirect("index.jsp");
            } else {
                // Rol desconocido
                request.setAttribute("errorMessage", "Rol desconocido.");
                request.getRequestDispatcher("iniciarSesionUsuario.jsp").forward(request, response);
            }
        } else {
            // Enviar un mensaje de error
            request.setAttribute("errorMessage", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("iniciarSesionUsuario.jsp").forward(request, response); // Redirigir de vuelta al formulario
        }
    }
}
