<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contraseña</title>

    <link rel="stylesheet" href="css/iniciarsesionysuscripcion.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" 
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
            crossorigin="anonymous"></script>
</head>
<body>
    <div class="container" style="width: 400px; margin-top: 50px;">
        <div class="text-center mt-3">
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid">
        </div>

        <h3 class="text-center mb-4"><b>Recuperar Contraseña</b></h3>

        <!-- Formulario de recuperación -->
        <form id="recuperarForm" action="enviarRecuperacion" method="post">
            <div class="mb-3">
                <input type="email" id="emailUsuario" name="emailUsuario" class="form-control" placeholder="Introduzca su correo" required>
            </div>

            <button type="submit" class="btn btn-dark w-100">Enviar enlace</button>
        </form>

        <div class="text-center mt-3">
            <p><a href="index.jsp">Volver al menú</a></p>
        </div>

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
