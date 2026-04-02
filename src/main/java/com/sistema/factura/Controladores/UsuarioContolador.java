package com.sistema.factura.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Servicios.UsuarioServicio;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@NoArgsConstructor
public class UsuarioContolador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
         System.out.println(usuario.getNombre());
        System.out.println(usuario.getEmail());
        return usuarioServicio.guardarUsuario(usuario);
    }
    
    @GetMapping
    public List<Usuario> allUsuarios(){
        return usuarioServicio.allUsuarios();
    }
}
