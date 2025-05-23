package servicios;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.RegistroUsuarioDto;
import jakarta.mail.MessagingException;

/**
 * Servicio para manejar el registro de usuarios
 * <p>
 * Este servicio se comunica con una API externa para registrar usuarios,
 * enviando sus datos a través de solicitudes HTTP POST. Utiliza objetos DTO para
 * representar los datos a registrar y convertirlos en formato JSON.
 * </p>
 */
public class RegistroServicio {

    /**
     * Registra un nuevo usuario a través de la API.
     * <p>
     * Este método envía una solicitud HTTP POST con los datos de un usuario en
     * formato JSON. Si la respuesta es exitosa, el usuario es registrado.
     * </p>
     * 
     * @param registroDto el objeto DTO que contiene los datos del usuario a registrar.
     * @return {@code true} si el registro fue exitoso; {@code false} en caso contrario.
     */
    public boolean registrarUsuario(RegistroUsuarioDto registroDto) {
        boolean registroExitoso = false;

        try {
            // Configurar la URL de la API para el registro
            URL url = new URL("http://localhost:8081/api/registro/usuario");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Convertir el DTO de usuario a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = mapper.writeValueAsString(registroDto);

            // Enviar la solicitud al servidor
            try (OutputStream os = conexion.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            // Obtener el código de respuesta
            int responseCode = conexion.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) { // 201 Created
                // Si el usuario se creó correctamente
                registroExitoso = true;
                
             // Método que envía un correo al usuario para verificar su cuenta 
                EmailServicio rc = new EmailServicio();
                try {
                	rc.enviarCorreo(
                		    registroDto.getEmailUsuario(),
                		    "Bienvenido a El Diario Fuun – Confirma tu suscripción",
                		    "¡Hola " + registroDto.getNombreUsuario() + "!\n\n" +
                		    "Te damos la bienvenida a *El Diario Fuun*, tu nueva fuente confiable de noticias, análisis y actualidad.\n\n" +
                		    "Para completar tu suscripción y comenzar a recibir nuestras noticias, necesitamos verificar tu dirección de correo electrónico.\n\n" +
                		    "Haz clic en el siguiente enlace para confirmar tu cuenta:\n\n" +
                		    "enlace\n\n" +
                		    "Una vez confirmada, tendrás acceso a contenido exclusivo, notificaciones personalizadas y mucho más.\n\n" +
                		    "Si no solicitaste esta suscripción, simplemente ignora este mensaje. Tus datos no serán guardados hasta que verifiques tu cuenta.\n\n" +
                		    "Gracias por unirte a nuestra comunidad informada.\n\n" +
                		    "Atentamente,\n" +
                		    "El equipo de El Diario Fuun"
                		);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                
            } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) { // 409 Conflict
                // Si hubo un conflicto, como correo duplicado
                System.out.println("Error: El correo ya está registrado.");
            } else {
                System.out.println("Error: Código de respuesta no esperado. Código: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return registroExitoso;
    }

}
