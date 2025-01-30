package controladores;

import java.io.IOException;

import Dtos.RegistroClubDto;
import Servicios.RegistroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador para manejar el registro de clubes.
 * <p>
 * Este servlet procesa solicitudes HTTP POST para registrar un club,
 * utilizando un servicio específico para realizar la operación.
 * </p>
 * <p>
 * Los datos del club se envían en un formulario y se encapsulan en un DTO
 * para ser procesados por el servicio.
 * </p>
 */
@WebServlet("/registroClub")
public class RegistroClubControlador extends HttpServlet {

    /**
     * Servicio encargado de manejar la lógica de registro del club.
     */
    private RegistroServicio registroServicio;

    /**
     * Inicializa el controlador configurando el servicio de registro.
     * 
     * @throws ServletException si ocurre un error en la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        // Inicializar el servicio de registro de clubes
        this.registroServicio = new RegistroServicio();
    }

    /**
     * Procesa las solicitudes HTTP POST para registrar un club.
     * <p>
     * Este método recoge los datos enviados desde el formulario, los encapsula en
     * un DTO, y llama al servicio para registrar el club en el sistema. Si el
     * registro es exitoso, redirige al formulario de inicio de sesión; de lo
     * contrario, muestra un mensaje de error.
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
        // Recuperar los datos del formulario
        String nombre = request.getParameter("nombreClub");
        String sede = request.getParameter("sedeClub");
        String correo = request.getParameter("emailClub");
        String password = request.getParameter("passwordClub");

        // Crear y configurar el DTO para el registro
        RegistroClubDto registroDto = new RegistroClubDto();
        registroDto.setNombreClub(nombre);
        registroDto.setSedeClub(sede);
        registroDto.setEmailClub(correo);
        registroDto.setPasswordClub(password);

        // Llamar al servicio para registrar el club
        boolean registroExitoso = registroServicio.registrarClub(registroDto);

        if (registroExitoso) {
            // Redirigir al formulario de inicio de sesión si el registro es exitoso
            response.sendRedirect("iniciarSesionClub.jsp");
        } else {
            // Mostrar un mensaje de error si el registro falla
            request.setAttribute("errorMessage", "El correo ya está registrado.");
            request.getRequestDispatcher("registrarseUsuario.jsp").forward(request, response);
        }
    }
}
