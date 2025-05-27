package servicios;

import dtos.RecuperacionContrasenaDto;
import utilidades.EmailUtilidad;
import utilidades.TokenUtilidad;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecuperacionContrasenaServicio {

    public boolean emailExisteEnApi(String email) {
        try {
            URL url = new URL("http://localhost:8081/api/usuarios/existe?email=" + email);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            return conexion.getResponseCode() == HttpURLConnection.HTTP_OK &&
                    Boolean.parseBoolean(new java.io.BufferedReader(
                            new java.io.InputStreamReader(conexion.getInputStream())).readLine());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enviarEnlaceRecuperacion(String email, String token) {
        try {
            EmailUtilidad emailUtil = new EmailUtilidad();
            String enlace = "http://localhost:8080/vistaFuun/restablecerContrasena.jsp?token=" + token;
            String asunto = "Restablecimiento de contraseña – El Diario Fuun";
            String cuerpo = "Haz clic en el siguiente enlace para restablecer tu contraseña:\n\n" +
                    enlace + "\n\nSi no solicitaste esta acción, ignora este correo.";
            emailUtil.enviarCorreo(email, asunto, cuerpo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarContrasena(RecuperacionContrasenaDto dto) {
        try {
        	URL url = new URL("http://localhost:8081/api/usuarios/recuperar-password");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexion.setDoOutput(true);

            String parametros = "email=" + dto.getEmailUsuario() +
                    "&nuevaPassword=" + dto.getNuevaPassword();

            try (OutputStream os = conexion.getOutputStream()) {
                os.write(parametros.getBytes());
            }

            return conexion.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
