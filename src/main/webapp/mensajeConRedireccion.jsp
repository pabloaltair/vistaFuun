<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Contraseña Restablecida</title>

    <!-- Enlace a estilos CSS -->
    <link rel="stylesheet" href="css/iniciarsesionysuscripcion.css" />
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" 
          crossorigin="anonymous" />
    <!-- Scripts de Bootstrap y jQuery -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        crossorigin="anonymous"></script>

    <script type="text/javascript">
        let tiempoRestante = 5;
        function cuentaAtras() {
            if (tiempoRestante > 0) {
                document.getElementById("contador").innerText = tiempoRestante;
                tiempoRestante--;
            } else {
                // Redirigir a IniciarSesionUsuario (ajusta si es JSP o servlet)
                window.location.href = "iniciarSesionUsuario.jsp";
            }
        }
        setInterval(cuentaAtras, 1000);
    </script>
</head>
<body>
    <div class="container" style="width: 400px; margin-top: 50px;">
        <!-- Imagen del logo -->
        <div class="text-center mt-3">
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid" />
        </div>

        <h3 class="text-center mb-4"><b>Contraseña Restablecida</b></h3>

        <div class="alert alert-success text-center" role="alert" style="font-size: 1.1rem;">
            ¡Contraseña restablecida exitosamente!<br />
            Serás redirigido a iniciar sesión en <span id="contador">5</span> segundos.
        </div>

    </div>
</body>
</html>
