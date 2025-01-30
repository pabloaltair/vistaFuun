package controladores;

import java.io.IOException;

import Servicios.ModificarServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Controlador para manejar la modificación de un club.
 * <p>
 * Este servlet procesa solicitudes HTTP POST para modificar la información de un club,
 * incluyendo su nombre, sede y foto, utilizando un servicio específico.
 * </p>
 * <p>
 * Soporta la subida de archivos utilizando la anotación {@link MultipartConfig}.
 * </p>
 */
@WebServlet("/modificarClub")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class ModificarClubControlador extends HttpServlet {

    /**
     * Servicio encargado de manejar la lógica de modificación del club.
     */
    private ModificarServicio modificarServicio;

    /**
     * Inicializa el controlador configurando el servicio de modificación.
     * 
     * @throws ServletException si ocurre un error en la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        this.modificarServicio = new ModificarServicio(); // Inicializa el servicio aquí
    }

    /**
     * Procesa las solicitudes HTTP POST para modificar un club.
     * <p>
     * Este método recoge los datos enviados desde el formulario, incluyendo el ID del club,
     * el nuevo nombre, la nueva sede y la nueva foto del club. Llama al servicio para
     * realizar la modificación y redirige al usuario a la página de menú del administrador
     * con un mensaje de resultado.
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
        try {
            // Recuperar parámetros del formulario
            long idClub = Long.parseLong(request.getParameter("idClub"));
            String nuevoNombre = request.getParameter("nuevoNombre");
            String nuevaSede = request.getParameter("nuevaSede");

            // Procesar el archivo de la foto
            Part fotoPart = request.getPart("nuevaFoto");
            byte[] nuevaFoto = null;

            if (fotoPart != null && fotoPart.getSize() > 0) {
                nuevaFoto = fotoPart.getInputStream().readAllBytes();
            }

            // Llamar al servicio API
            String resultado = modificarServicio.modificarClub(idClub, nuevoNombre, nuevaSede, nuevaFoto);

            // Redirigir con el resultado
            request.setAttribute("resultado", resultado);
            request.getRequestDispatcher("menuAdministrador.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejo de errores durante la ejecución
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
