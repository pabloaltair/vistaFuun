package utilidades;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utilidad para generación y gestión de tokens de verificación temporales.
 * Esta clase es reutilizable para múltiples flujos que requieran confirmación por token.
 */
public class TokenUtilidad {

    // Mapa temporal para asociar tokens a correos o identificadores únicos
    private static final Map<String, String> tokenUsuarioMap = new ConcurrentHashMap<>();
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    /**
     * Genera un token aleatorio y lo asocia a un usuario (por su correo electrónico).
     * 
     * @param emailUsuario el correo del usuario a quien se le genera el token.
     * @return el token generado.
     */
    public static String generarYAsignarToken(String emailUsuario) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);

        // Asociar token temporalmente al usuario
        tokenUsuarioMap.put(token, emailUsuario);
        return token;
    }

    /**
     * Verifica si el token recibido es válido y devuelve el email asociado si lo es.
     * 
     * @param token el token a validar.
     * @return el email del usuario si el token es válido; null si no lo es.
     */
    public static String validarToken(String token) {
        return tokenUsuarioMap.get(token);
    }

    /**
     * Elimina el token una vez utilizado o expirado.
     * 
     * @param token el token a eliminar.
     */
    public static void eliminarToken(String token) {
        tokenUsuarioMap.remove(token);
    }

    /**
     * Genera un enlace de verificación que puede ser enviado por correo electrónico.
     * 
     * @param token el token generado previamente.
     * @return una URL de verificación.
     */
    public static String generarEnlaceVerificacion(String token) {
        // Esta URL debe coincidir con tu ruta para validar el token
        return "http://localhost:8080/verificarCuenta?token=" + token;
    }
}
