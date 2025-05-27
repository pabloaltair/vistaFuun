package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dtos.ListaUsuarioDto;
import servicios.ListaUsuarioServicio;

import java.util.List;

@Controller
public class ListaUsuarioControlador {

    @Autowired
    private ListaUsuarioServicio listaUsuarioServicio;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<ListaUsuarioDto> usuarios = listaUsuarioServicio.obtenerUsuarios();
        System.out.println("Usuarios obtenidos: " + usuarios);
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
}
