<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dar de Alta Usuario</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container" style="max-width: 400px; margin-top: 50px;">
        <h3 class="text-center mb-4"><b>Alta de Usuario</b></h3>

        <!-- Formulario de registro -->
        <form id="registerUserForm" action="registroUsuario" method="POST"> 
        	<!-- Nickname de usuario -->
            <div class="mb-3">
                <input type="text" id="nicknameUsuario" name="nicknameUsuario" class="form-control" placeholder="Introduzca su nickname" required>
            </div>
            
            <!-- Nombre de usuario -->
            <div class="mb-3">
                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" placeholder="Introduzca su nombre" required>
            </div>
            
            <!-- DNI de usuario -->
            <div class="mb-3">
                <input type="text" id="dniUsuario" name="dniUsuario" class="form-control" placeholder="Introduzca su DNI" required>
            </div>
            
            <!-- Telefono de usuario -->
            <div class="mb-3">
                <input type="text" id="telefonoUsuario" name="telefonoUsuario" class="form-control" placeholder="Introduzca su telefono" required>
            </div>
        
            <!-- Correo -->
            <div class="mb-3">
                <input type="email" id="emailUsuario" name="emailUsuario" class="form-control" placeholder="Introduzca su email" required>
            </div>

            <!-- Contraseña -->
            <div class="mb-3">
                <input type="password" id="passwordUsuario" name="passwordUsuario" class="form-control" placeholder="Introduce la contraseña"
                    required>
            </div>

            <!-- Confirmar Contraseña -->
            <div class="mb-3">
                <input type="password" id="confirmPasswordUsuario" name="confirmPasswordUsuario" class="form-control"
                    placeholder="Repetir contraseña" required>
            </div>

            <!-- Botón de registro -->
            <button type="submit" class="btn btn-dark w-100">Registrarse</button>
        </form>

        <!-- Enlaces adicionales -->
        <div class="text-center mt-3">
            <p>¿Ya estas registrado? <a href="iniciarSesionUsuario.jsp">Iniciar Sesión</a></p>
            <p><a href="index.jsp">Volver al menú</a></p>
        </div>

        <!-- Contenedor de mensajes de resultado -->
        <div id="result" class="text-center mt-3 text-danger"></div>
    </div>
</body>

</html>