<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error de Confirmación</title>
    <link rel="stylesheet" href="css/iniciarsesionysuscripcion.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container" style="max-width: 500px; margin-top: 50px;">

        <!-- Imágen del logo -->
        <div class="text-center mt-3">
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid">
        </div>

        <h3 class="text-center my-4"><b>Error en la Confirmación</b></h3>

        <!-- Mensaje de error -->
        <div class="alert alert-danger text-center" role="alert">
            El enlace de confirmación es inválido o ha expirado. Por favor, verifica tu correo o contacta con soporte.
        </div>

        <!-- Botón para volver al inicio o intentar nuevamente -->
        <div class="text-center">
            <a href="index.jsp" class="btn btn-dark w-100">Volver al menú principal</a>
        </div>

        <div class="text-center mt-3">
            <p>¿No recibiste el correo? <a href="registrarseUsuario.jsp">Registrarse de nuevo</a></p>
        </div>

    </div>
</body>

</html>
