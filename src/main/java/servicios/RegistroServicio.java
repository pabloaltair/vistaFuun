package servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.RegistroUsuarioDto;
import jakarta.mail.MessagingException;
import utilidades.TokenUtilidad;

public class RegistroServicio {

    public boolean registrarUsuario(RegistroUsuarioDto registroDto) {
        boolean envioCorreoExitoso = false;

        try {
            // Generar token de verificación y enlace
            String token = TokenUtilidad.generarYAsignarToken(registroDto);
            String enlaceVerificacion = TokenUtilidad.generarEnlaceVerificacion(token);

            // Enviar correo
            EmailServicio emailServicio = new EmailServicio();
            String asunto = "Bienvenido a El Diario Fuun – Confirma tu suscripción";
            String cuerpo = "¡Hola " + registroDto.getNombreUsuario() + "!\n\n" +
                    "Te damos la bienvenida a *El Diario Fuun*, tu fuente confiable de noticias.\n\n" +
                    "Para completar tu suscripción, necesitamos verificar tu dirección de correo electrónico.\n\n" +
                    "Haz clic en el siguiente enlace para confirmar tu cuenta:\n\n" +
                    enlaceVerificacion + "\n\n" +
                    "Si no solicitaste esta suscripción, simplemente ignora este mensaje.\n\n" +
                    "Gracias por unirte a nuestra comunidad.\n\n" +
                    "El equipo de El Diario Fuun";

            emailServicio.enviarCorreo(registroDto.getEmailUsuario(), asunto, cuerpo);
            envioCorreoExitoso = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return envioCorreoExitoso;
    }

    public boolean insertarUsuarioEnApi(RegistroUsuarioDto registroDto) {
        boolean registroExitoso = false;

        try {
            URL url = new URL("http://localhost:8081/api/registro/usuario");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = mapper.writeValueAsString(registroDto);

            try (OutputStream os = conexion.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            int responseCode = conexion.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                registroExitoso = true;
            } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
                System.out.println("Error: El correo ya está registrado.");
            } else {
                System.out.println("Error: Código de respuesta no esperado: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("ERROR al insertar en API: " + e.getMessage());
        }

        return registroExitoso;
    }


    public boolean emailYaRegistrado(String email) {
        try {
            URL url = new URL("http://localhost:8081/api/usuarios/existe?email=" + email);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int responseCode = conexion.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String response = in.readLine(); // "true" o "false"
                in.close();
                return Boolean.parseBoolean(response); // <--- Aquí interpretamos el cuerpo correctamente
            } else {
                System.out.println("Error al consultar el email. Código HTTP: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // En caso de error, asumimos que el email no está registrado (pero puedes cambiar esto según tu lógica)
        return false;
    }


}