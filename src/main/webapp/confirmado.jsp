<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cuenta Confirmada</title>
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

        <h3 class="text-center my-4"><b>¡Cuenta Confirmada!</b></h3>

        <!-- Mensaje de éxito -->
        <div class="alert alert-success text-center" role="alert">
            Tu cuenta ha sido confirmada con éxito. Ya puedes iniciar sesión en la plataforma.
        </div>

        <!-- Botón para ir a iniciar sesión -->
        <div class="text-center">
            <a href="iniciarSesionUsuario.jsp" class="btn btn-dark w-100">Iniciar Sesión</a>
        </div>

        <!-- Enlace adicional -->
        <div class="text-center mt-3">
            <a href="index.jsp">Volver al menú principal</a>
        </div>

    </div>
</body>

</html>
