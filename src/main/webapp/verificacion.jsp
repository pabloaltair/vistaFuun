<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verificación de Cuenta</title>
    <link rel="stylesheet" href="css/iniciarsesionysuscripcion.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container" style="max-width: 500px; margin-top: 60px;">
        <div class="text-center">
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid mb-4">
        </div>

        <div class="alert alert-info text-center" role="alert">
            Se ha enviado un enlace de verificación a la dirección de correo 
            <strong><%= request.getParameter("email") %></strong>. <br>
            Por favor, revise su bandeja de entrada y confirme su cuenta para completar el registro.
        </div>

        <div class="text-center mt-3">
            <p>Esta página se actualizará automáticamente una vez que verifique su cuenta.</p>
        </div>

        <!-- Lógica futura: refrescar y redirigir tras verificación -->
        <script>
            // Simulación de verificación futura (ejemplo de polling)
            // Este bloque se reemplazará con una llamada real al backend para saber si se ha verificado
            setInterval(function () {
                // Aquí deberías hacer una llamada AJAX para comprobar si el correo fue verificado
                // Por ahora, esto es solo un ejemplo simulado:

                // Simulador de redirección tras verificación (para el futuro)
                // if (verificado === true) {
                //     window.location.href = 'iniciarSesionUsuario.jsp';
                // }

                // Ejemplo temporal: Redirigir manualmente tras 10 segundos (simulación)
                setTimeout(function () {
                    window.location.href = 'iniciarSesionUsuario.jsp';
                }, 10000); // 10 segundos
            }, 10000);
        </script>
    </div>
</body>

</html>
