package com.sistema.factura.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Repositorios.UsuarioRepositorio;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepositorio.findAll();
    }
    public Usuario obtenerUsuarioPorId(Long id){
        return usuarioRepositorio.findById(id).orElse(null);
    }
    public void deleteUsuario(Long id){
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        if(usuario != null){
            usuarioRepositorio.delete(usuario);
        }
    }
    public Optional<Usuario> fyUsuarioUsername(String username){
        return usuarioRepositorio.findByUsername(username);
    }

}
