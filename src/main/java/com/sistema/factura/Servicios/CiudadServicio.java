package com.sistema.factura.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.factura.Entidades.Ciudad;
import com.sistema.factura.Repositorios.CiudadRepositorio;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CiudadServicio {
    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    public Ciudad guardarCiudad(Ciudad ciudad){
        return ciudadRepositorio.save(ciudad);
    }
    public void eliminarCiudad(Long id){
        ciudadRepositorio.deleteById(id);
    }
    public List<Ciudad> allCiudades(){
        return ciudadRepositorio.findAll();
    }
    public Optional<Ciudad> buscarCiudadPorId(Long id){
        return ciudadRepositorio.findById(id);
    }
}
