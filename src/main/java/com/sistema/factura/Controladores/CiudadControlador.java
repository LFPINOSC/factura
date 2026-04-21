package com.sistema.factura.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.factura.Entidades.Ciudad;
import com.sistema.factura.Servicios.CiudadServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ciudad")
@RequiredArgsConstructor
public class CiudadControlador {

    private final CiudadServicio ciudadServicio;

    @PostMapping
    public ResponseEntity<?> guardarCiudad(@RequestBody Ciudad ciudad) {
        try {
            if (ciudad == null) {
                return ResponseEntity.badRequest().body("Los datos de la ciudad no pueden estar vacíos.");
            }

            Ciudad ciudadGuardada = ciudadServicio.guardarCiudad(ciudad);
            return ResponseEntity.status(HttpStatus.CREATED).body(ciudadGuardada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la ciudad: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> allCiudades() {
        try {
            List<Ciudad> ciudades = ciudadServicio.allCiudades();

            if (ciudades.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen ciudades registradas.");
            }

            return ResponseEntity.ok(ciudades);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las ciudades: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCiudad(@PathVariable Long id) {
        try {
            Optional<Ciudad> ciudad = ciudadServicio.buscarCiudadPorId(id);

            if (ciudad.isPresent()) {
                return ResponseEntity.ok(ciudad.get());
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la ciudad con id: " + id);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar la ciudad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        try {
            Optional<Ciudad> ciudadExistente = ciudadServicio.buscarCiudadPorId(id);

            if (ciudadExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe la ciudad con id: " + id);
            }

            Ciudad ciudadActual = ciudadExistente.get();

            // Aquí debes ajustar los campos según tu entidad Ciudad
            ciudadActual.setNombre(ciudad.getNombre());

            Ciudad ciudadActualizada = ciudadServicio.guardarCiudad(ciudadActual);
            return ResponseEntity.ok(ciudadActualizada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la ciudad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable Long id) {
        try {
            Optional<Ciudad> ciudadExistente = ciudadServicio.buscarCiudadPorId(id);

            if (ciudadExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe la ciudad con id: " + id);
            }

            ciudadServicio.eliminarCiudad(id);
            return ResponseEntity.ok("Ciudad eliminada correctamente.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la ciudad: " + e.getMessage());
        }
    }
}