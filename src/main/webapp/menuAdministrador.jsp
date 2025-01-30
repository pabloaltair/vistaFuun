<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrador</title>
<link rel="stylesheet" href="css/administrador.css">
<link rel="stylesheet" href="css/index.css">
<link
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
   rel="stylesheet" />
<!-- Bootstrap CSS -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
   rel="stylesheet">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
   function mostrarSeccion(seccionId) {
       // Oculta todas las secciones
       const secciones = document.querySelectorAll('.seccion');
       secciones.forEach(seccion => seccion.style.display = 'none');
       // Muestra solo la sección seleccionada
       document.getElementById(seccionId).style.display = 'block';
   }
   // Mostrar la primera sección por defecto
   document.addEventListener('DOMContentLoaded', function () {
       mostrarSeccion('modificarUsuario');
   });
</script>
</head>
<body>
   <!-- Navbar -->
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
       <div class="container-fluid">
           <a class="navbar-brand" href="#">
               <img src="imagenes/logo moto.jpg" alt="Yamaha Logo" width="100" height="50">
           </a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
               data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
               aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse" id="navbarNavDropdown">
               <ul class="navbar-nav">
                   <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
                   <li class="nav-item"><a class="nav-link" href="#">Nosotros</a></li>
                   <li class="nav-item"><a class="nav-link" href="#">Eventos</a></li>
                   <li class="nav-item"><a class="nav-link" href="#">Contacto</a></li>
                   <li class="nav-item"><a class="nav-link" href="#">Rutas</a></li>
                   <li class="nav-item"><a class="nav-link" href="#">Sedes</a></li>
               </ul>
           </div>
       </div>
   </nav>
   <div class="container-fluid">
       <div class="row">
           <!-- Menú lateral -->
           <div class="col-md-3 bg-light py-4">
               <h5>Menú</h5>
               <ul class="nav flex-column">
                   <li class="nav-item">
                       <button class="btn btn-link nav-link" onclick="mostrarSeccion('modificarUsuario')">Modificar Usuario</button>
                   </li>
                   <li class="nav-item">
                       <button class="btn btn-link nav-link" onclick="mostrarSeccion('modificarClub')">Modificar Club</button>
                   </li>
                   <li class="nav-item">
                       <button class="btn btn-link nav-link" onclick="mostrarSeccion('eliminarUsuario')">Eliminar Usuario</button>
                   </li>
                   <li class="nav-item">
                       <button class="btn btn-link nav-link" onclick="mostrarSeccion('eliminarClub')">Eliminar Club</button>
                   </li>
               </ul>
           </div>
           <!-- Contenido principal -->
           <div class="col-md-9 py-4">
               <!-- Sección: Modificar Usuario -->
               <div id="modificarUsuario" class="seccion">
                   <h2>Modificar Usuario</h2>
                   <form action="modificarUsuario" method="post" enctype="multipart/form-data">
                       <label for="idUsuario">ID Usuario:</label>
                       <input type="number" id="idUsuario" name="idUsuario" required>
                       <label for="nuevoNombre">Nuevo Nombre:</label>
                       <input type="text" id="nuevoNombre" name="nuevoNombre">
                       <label for="nuevoTelefono">Nuevo Teléfono:</label>
                       <input type="text" id="nuevoTelefono" name="nuevoTelefono">
                       <label for="nuevaFoto">Nueva Foto:</label>
                       <input type="file" id="nuevaFoto" name="nuevaFoto" accept="image/*">
                       <button type="submit" class="btn btn-primary mt-3">Modificar Usuario</button>
                   </form>
               </div>
               <!-- Sección: Modificar Club -->
               <div id="modificarClub" class="seccion" style="display: none;">
                   <h2>Modificar Club</h2>
                   <form action="modificarClub" method="post" enctype="multipart/form-data">
                       <label for="idClub">ID Club:</label>
                       <input type="number" id="idClub" name="idClub" required>
                       <label for="nuevoNombre">Nuevo Nombre:</label>
                       <input type="text" id="nuevoNombre" name="nuevoNombre">
                       <label for="nuevaSede">Nueva Sede:</label>
                       <input type="text" id="nuevaSede" name="nuevaSede">
                       <label for="nuevaFoto">Nueva Foto:</label>
                       <input type="file" id="nuevaFoto" name="nuevaFoto" accept="image/*">
                       <button type="submit" class="btn btn-primary mt-3">Modificar Club</button>
                   </form>
               </div>
               <!-- Sección: Eliminar Usuario -->
               <div id="eliminarUsuario" class="seccion" style="display: none;">
                   <h2>Eliminar Usuario</h2>
                   <form action="eliminarUsuario" method="post">
                       <label for="idUsuario">ID Usuario:</label>
                       <input type="number" id="idUsuario" name="idUsuario" required>
                       <button type="submit" class="btn btn-danger mt-3">Eliminar Usuario</button>
                   </form>
               </div>
               <!-- Sección: Eliminar Club -->
               <div id="eliminarClub" class="seccion" style="display: none;">
                   <h2>Eliminar Club</h2>
                   <form action="eliminarClub" method="post">
                       <label for="idClub">ID Club:</label>
                       <input type="number" id="idClub" name="idClub" required>
                       <button type="submit" class="btn btn-danger mt-3">Eliminar Club</button>
                   </form>
               </div>
               <!-- Contenedor de resultados -->
               <div class="mt-4">
                   <h3>Resultado:</h3>
                   <p class="alert alert-info">${resultado}</p>
               </div>
           </div>
       </div>
   </div>
</body>
</html>
