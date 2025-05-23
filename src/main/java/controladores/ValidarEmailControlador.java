package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RegistroServicio;

import java.io.IOException;

@WebServlet("/validarEmail")
public class ValidarEmailControlador extends HttpServlet {

    private RegistroServicio registroServicio;

    @Override
    public void init() throws ServletException {
        registroServicio = new RegistroServicio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("false");
            return;
        }
        
        boolean existe = registroServicio.emailYaRegistrado(email);
        response.setContentType("application/json");
        response.getWriter().write(Boolean.toString(existe));
    }
}
