package Dtos;

/**
 * Clase DTO para representar los datos de un club a modificar.
 * <p>
 * Este objeto se utiliza para enviar y recibir los datos del club a modificar, 
 * como el nombre y la sede, a través de la API.
 * </p>
 */
public class ModificarClubDto {

    private long idClub;
    private String nombreClub;
    private String sedeClub;

    // Getters & Setters

    public long getIdClub() {
        return idClub;
    }

    public void setIdClub(long idClub) {
        this.idClub = idClub;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String getSedeClub() {
        return sedeClub;
    }

    public void setSedeClub(String sedeClub) {
        this.sedeClub = sedeClub;
    }
}
