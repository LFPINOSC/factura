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

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }
    public List<Usuario> allUsuarios(){
        return usuarioRepositorio.findAll();
    }
    public void eliminarUsuario(Long id){
        usuarioRepositorio.deleteById(id);
    }
    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepositorio.findById(id);
    }
    public Optional<Usuario> buscarUsuarioPorUsername(String username){
        return usuarioRepositorio.findByUsername(username);
    }

}
