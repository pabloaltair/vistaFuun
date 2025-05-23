package utilidades;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dtos.RegistroUsuarioDto;

/**
 * Utilidad para generación y gestión de tokens de verificación temporales.
 * Esta clase es reutilizable para múltiples flujos que requieran confirmación por token.
 */
public class TokenUtilidad {

    // Mapa temporal para asociar tokens a objetos RegistroUsuarioDto completos
    private static final Map<String, RegistroUsuarioDto> tokenUsuarioMap = new ConcurrentHashMap<>();
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    /**
     * Genera un token aleatorio y lo asocia a un usuario (por su DTO completo).
     * 
     * @param usuario el DTO completo del usuario al que se le genera el token.
     * @return el token generado.
     */
    public static String generarYAsignarToken(RegistroUsuarioDto usuario) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);

        // Asociar token temporalmente al DTO usuario
        tokenUsuarioMap.put(token, usuario);
        return token;
    }

    /**
     * Verifica si el token recibido es válido y devuelve el DTO usuario asociado si lo es.
     * 
     * @param token el token a validar.
     * @return el DTO RegistroUsuarioDto si el token es válido; null si no lo es.
     */
    public static RegistroUsuarioDto validarToken(String token) {
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
        return "http://localhost:8080/vistaFuun/verificarCuenta?token=" + token;
    }
}
