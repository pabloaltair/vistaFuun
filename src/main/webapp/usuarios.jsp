<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dtos.ListaUsuarioDto" %>
<%@ page import="servicios.ListaUsuarioServicio" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios Registrados</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" 
          crossorigin="anonymous">
</head>
<body>

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
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ListaUsuarioDto usuario : listaUsuarios) {
                        int idUsuario = usuario.getIdUsuario(); // Se usa pero no se muestra
                %>
                <tr>
                    <td><%= usuario.getNombreUsuario() %></td>
                    <td><%= usuario.getEmailUsuario() %></td>
                    <td><%= usuario.getRol() %></td>
                    <td>
                        <!-- Botones sin funcionalidad por ahora -->
                        <button class="btn btn-sm btn-warning me-1">Modificar</button>
                        <button class="btn btn-sm btn-danger">Eliminar</button>
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

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        crossorigin="anonymous"></script>

</body>
</html>
