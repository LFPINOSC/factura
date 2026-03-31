package com.sistema.factura.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.factura.Entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente,Long>{
    Optional<Cliente> buscarPorCedula(String cedula);
}
