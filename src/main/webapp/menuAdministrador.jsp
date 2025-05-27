<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="dtos.ListaUsuarioDto" %>
<%@ page import="servicios.ListaUsuarioServicio" %>
<%
    // Obtener la sesión y verificar el rol
    HttpSession sesion = request.getSession(false);
    String rol = (sesion != null) ? (String) sesion.getAttribute("rol") : null;

    if (rol == null || !"admin".equals(rol)) {
        request.setAttribute("errorMessage", "No tienes permisos para acceder a esta página.");
        request.getRequestDispatcher("iniciarSesionUsuario.jsp").forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios Registrados</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>

<!-- Mensajes de error y éxito -->
<div class="text-center mt-3 text-danger">
    <% String errorMensaje = (String) request.getAttribute("errorMensaje");
       if (errorMensaje != null) { %>
        <p class="error"><%= errorMensaje %></p>
    <% } %>
</div>

<div class="text-center mt-3 text-success">
    <% String successMensaje = (String) request.getAttribute("successMensaje");
       if (successMensaje != null) { %>
        <p class="success"><%= successMensaje %></p>
    <% } %>
</div>
<!-- Fin mensajes -->

<div class="container mt-3">
    <c:if test="${not empty errorMensaje}">
        <div class="alert alert-danger text-center" role="alert">
            ${errorMensaje}
        </div>
    </c:if>
</div>

<div class="container mt-5">
    <div class="text-center mb-4">
        <img width="148" height="118" src="imagenes/logo.png" alt="Logo Fuun" class="img-fluid">
        <h2 class="mt-3"><b>Usuarios Registrados</b></h2>
    </div>

    <%
        ListaUsuarioServicio ls = new ListaUsuarioServicio();
        List<ListaUsuarioDto> listaUsuarios = ls.obtenerUsuarios();
        if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
    %>

    <div class="table-responsive">
        <table class="table table-bordered table-striped text-center align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ListaUsuarioDto usuario : listaUsuarios) {
                        int idUsuario = usuario.getIdUsuario();
                %>
                <tr>
                    <td><%= usuario.getNombreUsuario() %></td>
                    <td><%= usuario.getEmailUsuario() %></td>
                    <td><%= usuario.getTelefonoUsuario() %></td>
                    <td><%= usuario.getRol() %></td>
                    <td>
                        <button class="btn btn-warning btn-sm me-1"
                            onclick="llenarFormularioModificar('<%= idUsuario %>', '<%= usuario.getNombreUsuario() %>', '<%= usuario.getTelefonoUsuario() %>', '<%= usuario.getRol() %>')"
                            data-bs-toggle="modal" data-bs-target="#modalModificar">
                            Modificar
                        </button>
                        <button class="btn btn-danger btn-sm"
                            onclick="confirmarEliminacion('<%= idUsuario %>')"
                            data-bs-toggle="modal" data-bs-target="#modalEliminar">
                            Eliminar
                        </button>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <% } else { %>
        <p class="text-center text-danger">No hay usuarios registrados.</p>
    <% } %>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Volver al menú</a>
    </div>
</div>

<!-- Modal: Modificar Usuario -->
<div class="modal fade" id="modalModificar" tabindex="-1" aria-labelledby="modalModificarLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="modificarUsuario" method="post" enctype="multipart/form-data">
      <div class="modal-header">
        <h5 class="modal-title" id="modalModificarLabel">Modificar Usuario</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="idUsuario" id="modIdUsuario">

        <div class="mb-3">
          <label for="modNombre" class="form-label">Nuevo Nombre</label>
          <input type="text" class="form-control" name="nuevoNombre" id="modNombre" required>
        </div>

        <div class="mb-3">
          <label for="modTelefono" class="form-label">Nuevo Teléfono</label>
          <input type="text" class="form-control" name="nuevoTelefono" id="modTelefono" required>
        </div>

        <div class="mb-3">
          <label for="modRol" class="form-label">Rol</label>
          <select class="form-select" name="nuevoRol" id="modRol" required>
            <option value="admin">Admin</option>
            <option value="usuario">Usuario</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="modFoto" class="form-label">Nueva Foto (opcional)</label>
          <input type="file" class="form-control" name="nuevaFoto" id="modFoto" accept="image/*">
        </div>

      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
      </div>
    </form>
  </div>
</div>

<!-- Modal: Eliminar Usuario -->
<div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="eliminarUsuario" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="modalEliminarLabel">Confirmar Eliminación</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p>¿Estás seguro de que deseas eliminar este usuario?</p>
        <input type="hidden" name="idUsuario" id="elimIdUsuario">
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">Eliminar</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
      </div>
    </form>
  </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<script>
    function llenarFormularioModificar(id, nombre, telefono, rol) {
        document.getElementById("modIdUsuario").value = id;
        document.getElementById("modNombre").value = nombre;
        document.getElementById("modTelefono").value = telefono;
        document.getElementById("modRol").value = rol;
    }

    function confirmarEliminacion(id) {
        document.getElementById("elimIdUsuario").value = id;
    }
</script>

</body>
</html>
