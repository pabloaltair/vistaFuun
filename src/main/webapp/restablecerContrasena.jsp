<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restablecer Contraseña</title>

    <link rel="stylesheet" href="css/iniciarsesionysuscripcion.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container" style="width: 400px; margin-top: 50px;">
        <div class="text-center mt-3">
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid">
        </div>

        <h3 class="text-center mb-4"><b>Establecer nueva contraseña</b></h3>

        <form action="procesarCambioContrasena" method="post">
		    <!-- Token oculto pasado por URL -->
		    <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
		
		    <div class="mb-3">
		        <input type="password" name="nuevaPassword" class="form-control" placeholder="Nueva contraseña" required>
		    </div>
		
		    <div class="mb-3">
		        <input type="password" name="confirmarPassword" class="form-control" placeholder="Confirmar contraseña" required>
		    </div>
		
		    <button type="submit" class="btn btn-dark w-100">Actualizar contraseña</button>
		</form>


        <div class="text-center mt-3 text-danger">
            <% String errorMessage = (String) request.getAttribute("errorMessage");
               if (errorMessage != null) { %>
                <p class="error"><%= errorMessage %></p>
            <% } %>
        </div>

        <div class="text-center mt-3 text-success">
            <% String successMessage = (String) request.getAttribute("successMessage");
               if (successMessage != null) { %>
                <p class="success"><%= successMessage %></p>
            <% } %>
        </div>
    </div>
</body>
</html>
