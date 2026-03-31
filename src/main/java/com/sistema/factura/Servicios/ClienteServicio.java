package com.sistema.factura.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.factura.Entidades.Cliente;
import com.sistema.factura.Repositorios.ClienteRepositorio;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClienteServicio {
   @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }
    public void eliminarCliente(Long id){
        clienteRepositorio.deleteById(id);
    }
    public List<Cliente> allClientes(){
        return clienteRepositorio.findAll();
    }
    public Optional<Cliente> buscarClientePorId(Long id){
        return clienteRepositorio.findById(id);
    }
    public Optional<Cliente> buscarClientePorCedula(String cedula){
        return clienteRepositorio.findByCedula(cedula);
    }
}
