package servicios;

import dtos.ListaUsuarioDto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class EliminarServicio {

    private final ListaUsuarioServicio listaServicio = new ListaUsuarioServicio();

    public String eliminarUsuario(long idUsuario) {
        List<ListaUsuarioDto> usuarios = listaServicio.obtenerUsuarios();

        if (usuarios == null || usuarios.isEmpty()) {
            return "No hay usuarios registrados.";
        }

        int adminCount = 0;
        ListaUsuarioDto usuarioAEliminar = null;

        for (ListaUsuarioDto usuario : usuarios) {
            if ("admin".equalsIgnoreCase(usuario.getRol())) {
                adminCount++;
            }
            if (usuario.getIdUsuario() == idUsuario) {
                usuarioAEliminar = usuario;
            }
        }

        if (usuarioAEliminar == null) {
            return "Usuario no encontrado.";
        }

        if ("admin".equalsIgnoreCase(usuarioAEliminar.getRol()) && adminCount <= 1) {
            return "No se puede eliminar el último administrador.";
        }

        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://localhost:8081/api/eliminar/usuario/" + idUsuario);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                return "Usuario eliminado exitosamente.";
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                return "Usuario no encontrado.";
            } else {
                return "Error al eliminar el usuario. Código de respuesta: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al conectar con la API: " + e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
