package servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dtos.ListaUsuarioDto;

import java.util.Arrays;
import java.util.List;

@Service
public class ListaUsuarioServicio {

    private final String API_URL = "http://localhost:8081/api/usuarios/lista";

    public List<ListaUsuarioDto> obtenerUsuarios() {
        RestTemplate restTemplate = new RestTemplate();
        ListaUsuarioDto[] usuarios = restTemplate.getForObject(API_URL, ListaUsuarioDto[].class);
        System.out.println("Respuesta API: " + Arrays.toString(usuarios));
        return Arrays.asList(usuarios);
    }

}
