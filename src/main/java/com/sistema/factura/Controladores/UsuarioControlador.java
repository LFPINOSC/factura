package com.sistema.factura.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioServicio.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios(){
        return usuarioServicio.obtenerUsuarios();
    }
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.obtenerUsuarioPorId(id);
    }
    @DeleteMapping
    public void deleteUsuario(@PathVariable Long id){
        usuarioServicio.deleteUsuario(id);
    }
    
}
