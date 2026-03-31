package com.sistema.factura.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.sistema.factura.Entidades.Ciudad;
import com.sistema.factura.Servicios.CiudadServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ciudad")
@RequiredArgsConstructor
public class CiudadControlador {
    @Autowired
    private CiudadServicio ciudadServicio;

    @PostMapping
    public Ciudad guardarCiudad(@RequestBody Ciudad ciudad){
        return ciudadServicio.guardarCiudad(ciudad);
    }
    @GetMapping
    public List<Ciudad> allCiudades(){
        return ciudadServicio.allCiudades();
    }
    @DeleteMapping("/{id}")
    public void eliminarCiudad(@PathVariable Long id){
        ciudadServicio.eliminarCiudad(id);
    }
    @GetMapping("/{id}")
    public Optional<Ciudad> getCiudad(@PathVariable Long id){
        return ciudadServicio.buscarCiudadPorId(id);
    }
    @PutMapping("/{id}")
    public Ciudad actualizarCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad){
        return ciudadServicio.guardarCiudad(ciudad);
    }
}
