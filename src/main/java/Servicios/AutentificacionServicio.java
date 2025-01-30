package Servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dtos.LoginClubDto;
import Dtos.LoginUsuarioDto;

/**
 * Servicio para manejar la autenticación de usuarios y clubes.
 * <p>
 * Este servicio se comunica con una API externa para verificar credenciales
 * de inicio de sesión y determina el rol del usuario o del club.
 * </p>
 */
public class AutentificacionServicio {

    /**
     * Almacena el rol del usuario o club autenticado.
     */
    private String rol = "";

    /**
     * Verifica las credenciales de un usuario llamando a la API correspondiente.
     * <p>
     * Si las credenciales son válidas, el rol del usuario (por ejemplo, "admin" o
     * "usuario") se guarda y el método devuelve {@code true}.
     * </p>
     * 
     * @param correo   el correo electrónico del usuario.
     * @param password la contraseña del usuario.
     * @return {@code true} si las credenciales son válidas; {@code false} en caso contrario.
     */
    public boolean verificarUsuario(String correo, String password) {
        boolean todoOk = false;

        try {
            // Crear la URL de la API para la verificación del usuario
            URL url = new URL("http://localhost:8081/api/login/validarUsuario");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Crear el objeto DTO con las credenciales del usuario
            LoginUsuarioDto loginRequest = new LoginUsuarioDto();
            loginRequest.setEmail(correo);
            loginRequest.setPassword(password);

            // Convertir el DTO a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = mapper.writeValueAsString(loginRequest);

            // Enviar la solicitud al servidor
            try (OutputStream ot = conexion.getOutputStream()) {
                ot.write(jsonInput.getBytes());
                ot.flush();
            }

            // Procesar la respuesta del servidor
            int responseCode = conexion.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String respuesta = response.toString();
                    System.out.println("Respuesta del servidor: " + respuesta);

                    if ("admin".equals(respuesta) || "usuario".equals(respuesta)) {
                        this.rol = respuesta;
                        todoOk = true;
                    } else {
                        System.out.println("Rol desconocido o error en la respuesta.");
                    }
                }
            } else {
                System.out.println("Error: Código de respuesta no OK. Código: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }

        return todoOk;
    }

    /**
     * Verifica las credenciales de un club llamando a la API correspondiente.
     * <p>
     * Si las credenciales son válidas, el rol del club ("club") se guarda y el método
     * devuelve {@code true}.
     * </p>
     * 
     * @param correo   el correo electrónico del club.
     * @param password la contraseña del club.
     * @return {@code true} si las credenciales son válidas; {@code false} en caso contrario.
     */
    public boolean verificarClub(String correo, String password) {
        boolean todoOk = false;

        try {
            // Crear la URL de la API para la verificación del club
            URL url = new URL("http://localhost:8081/api/login/validarClub");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Crear el objeto DTO con las credenciales del club
            LoginClubDto loginRequest = new LoginClubDto();
            loginRequest.setEmailClub(correo);
            loginRequest.setPasswordClub(password);

            // Convertir el DTO a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = mapper.writeValueAsString(loginRequest);

            // Enviar la solicitud al servidor
            try (OutputStream ot = conexion.getOutputStream()) {
                ot.write(jsonInput.getBytes());
                ot.flush();
            }

            // Procesar la respuesta del servidor
            int responseCode = conexion.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String respuesta = response.toString();
                    System.out.println("Respuesta del servidor: " + respuesta);

                    if ("club".equals(respuesta)) {
                        this.rol = respuesta;
                        todoOk = true;
                    } else {
                        System.out.println("Respuesta inesperada o error en la respuesta.");
                    }
                }
            } else {
                System.out.println("Error: Código de respuesta no OK. Código: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }

        return todoOk;
    }

    /**
     * Obtiene el rol asignado al usuario o club autenticado.
     * 
     * @return el rol asignado.
     */
    public String getRol() {
        return rol;
    }
}
