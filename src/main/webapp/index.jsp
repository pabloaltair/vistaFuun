<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fun Memes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="imagenes/FAVICON.png" type="image/png"></link>
</head>
<body>
    <header class="container-fluid bg-light py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
                <input type="text" class="form-control me-2" placeholder="Buscar...">
                <button class="btn btn-outline-secondary">🔍</button>
            </div>
            <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid">
            <div>
                <%
                    String email = (String) session.getAttribute("email");
                    if (email != null) {
                %>
                    <a href="cuentaUsuario.jsp" class="me-3">Cuenta</a>
                    <a href="logout" class="">Cerrar sesión</a>
                <%
                    } else {
                %>
                    <a href="iniciarSesionUsuario.jsp" class="me-3">Iniciar Sesión</a>
                    <a href="registrarseUsuario.jsp">Suscribirse</a>
                <%
                    }
                %>
            </div>
        </div>
    </header>

    <nav class="container mt-3">
        <ul class="nav nav-tabs justify-content-center">
            <li class="nav-item">
                <a class="nav-link active" href="#">Clasicos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Memes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Videojuegos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Tendencias</a>
            </li>
        </ul>
    </nav>

    <main class="container mt-4">
        <!-- Carrusel Bootstrap -->
        <div id="carruselMemes" class="carousel slide mb-4" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carruselMemes" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carruselMemes" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carruselMemes" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="imagenes/Carrusel1.png" class="d-block w-100" alt="Meme Pepe the Frog">
                    <div class="carousel-caption d-none d-md-block">
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="imagenes/Carrusel1.png" class="d-block w-100" alt="Hombre mirando a otra chica">
                    <div class="carousel-caption d-none d-md-block">
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="imagenes/Carrusel1.png" class="d-block w-100" alt="Hombre sorprendido">
                    <div class="carousel-caption d-none d-md-block">
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carruselMemes" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Anterior</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carruselMemes" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Siguiente</span>
            </button>
        </div>

        <div class="row">
            <!-- Imagen principal -->
            <div class="col-md-6 mb-4">
                <img width="500" height="300" src="imagenes/pepethefrog.png" alt="Clasico Pepe Meme" class="img-fluid">
                <h4 class="mt-3">CLASICOS DE INTERNET</h4>
                <p class="fw-bold">Lorem ipsum</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa mi, luctus a bibendum non, consequat blandit sapien.</p>
            </div>

            <!-- Memes actuales -->
            <div class="col-md-6 mb-4">
                <img width="500" height="300" src="imagenes/hombremirandootrachica.png" alt="Clasico Pepe Meme" class="img-fluid">
                <h4 class="mt-3">CLASICOS DE INTERNET</h4>
                <p class="fw-bold">Lorem ipsum</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa mi, luctus a bibendum non, consequat blandit sapien.</p>
            </div>
        </div>

        <div class="row">
            <!-- Memes adicionales -->
            <div class="col-md-6 mb-4">
                <img width="500" height="300" src="imagenes/chillguy.png" alt="Clasico Pepe Meme" class="img-fluid">
                <h4 class="mt-3">CLASICOS DE INTERNET</h4>
                <p class="fw-bold">Lorem ipsum</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa mi, luctus a bibendum non, consequat blandit sapien.</p>
            </div>

            <div class="col-md-6 mb-4">
                <img width="500" height="300" src="imagenes/hombresorprendido.png" alt="Clasico Pepe Meme" class="img-fluid">
                <h4 class="mt-3">CLASICOS DE INTERNET</h4>
                <p class="fw-bold">Lorem ipsum</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa mi, luctus a bibendum non, consequat blandit sapien.</p>
            </div>
        </div>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
