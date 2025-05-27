package controladores;

import java.io.IOException;
import servicios.EliminarServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eliminarUsuario")
public class EliminarUsuarioControlador extends HttpServlet {

    private final EliminarServicio eliminarServicio = new EliminarServicio();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

            String resultado = eliminarServicio.eliminarUsuario(idUsuario);

            // Diferenciamos mensaje de éxito o error según texto:
            if (resultado.toLowerCase().contains("error") || 
                resultado.toLowerCase().contains("no se puede") || 
                resultado.toLowerCase().contains("no encontrado") || 
                resultado.toLowerCase().contains("no hay")) {
                request.setAttribute("errorMessage", resultado);
            } else {
                request.setAttribute("successMessage", resultado);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el usuario: " + e.getMessage());
        }

        request.getRequestDispatcher("menuAdministrador.jsp").forward(request, response);
    }
}
