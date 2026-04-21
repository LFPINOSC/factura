package com.sistema.factura.Controladores;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.factura.Entidades.Cliente;
import com.sistema.factura.Servicios.ClienteServicio;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ClienteControlador {
    @Autowired
    private final ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteServicio.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteServicio.allClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteServicio.buscarClientePorId(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado con id: " + id);
        }
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<?> buscarClientePorCedula(@PathVariable String cedula) {
        Optional<Cliente> cliente = clienteServicio.buscarClientePorCedula(cedula);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado con cédula: " + cedula);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteServicio.buscarClientePorId(id);

        if (cliente.isPresent()) {
            clienteServicio.eliminarCliente(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe cliente con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = clienteServicio.buscarClientePorId(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();

            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setCedula(clienteActualizado.getCedula());
            cliente.setCorreo(clienteActualizado.getCorreo());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setDireccion(clienteActualizado.getDireccion());

            Cliente clienteGuardado = clienteServicio.guardarCliente(cliente);
            return ResponseEntity.ok(clienteGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe cliente con id: " + id);
        }
    }
}
