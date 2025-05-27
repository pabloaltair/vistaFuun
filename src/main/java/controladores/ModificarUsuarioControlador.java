package controladores;

import java.io.IOException;

import servicios.ModificarServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Controlador para manejar la modificación de un usuario.
 */
@WebServlet("/modificarUsuario")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class ModificarUsuarioControlador extends HttpServlet {

    private ModificarServicio modificarServicio;

    @Override
    public void init() throws ServletException {
        this.modificarServicio = new ModificarServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            String nuevoNombre = request.getParameter("nuevoNombre");
            String nuevoTelefono = request.getParameter("nuevoTelefono");
            String nuevoRol = request.getParameter("nuevoRol");

            Part fotoPart = request.getPart("nuevaFoto");
            byte[] nuevaFoto = null;

            if (fotoPart != null && fotoPart.getSize() > 0) {
                nuevaFoto = fotoPart.getInputStream().readAllBytes();
            }

            String resultado = modificarServicio.modificarUsuario(
                    idUsuario, nuevoNombre, nuevoTelefono, nuevoRol, nuevaFoto);

            request.setAttribute("successMensaje", resultado);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMensaje", "ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMensaje", "Error al modificar el usuario: " + e.getMessage());
        }

        request.getRequestDispatcher("menuAdministrador.jsp").forward(request, response);
    }
}
