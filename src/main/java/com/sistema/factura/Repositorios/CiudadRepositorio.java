package com.sistema.factura.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.factura.Entidades.Ciudad;

public interface CiudadRepositorio extends JpaRepository<Ciudad,Long>{
    
}
