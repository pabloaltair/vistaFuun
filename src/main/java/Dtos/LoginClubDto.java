package Dtos;

/**
 * Clase DTO para representar las credenciales de un club al momento de iniciar sesión.
 * <p>
 * Este objeto se utiliza para enviar y recibir los datos de inicio de sesión del club, 
 * como el correo electrónico y la contraseña, a través de la API.
 * </p>
 */
public class LoginClubDto {

    private Long idClub;
    private String emailClub;
    private String passwordClub;

    // Getters y setters

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getEmailClub() {
        return emailClub;
    }

    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    public String getPasswordClub() {
        return passwordClub;
    }

    public void setPasswordClub(String passwordClub) {
        this.passwordClub = passwordClub;
    }
}
