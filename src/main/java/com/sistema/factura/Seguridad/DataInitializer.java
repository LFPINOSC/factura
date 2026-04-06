package com.sistema.factura.Seguridad;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Servicios.UsuarioServicio;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsuarios(UsuarioServicio usuarioServicio) {
        return args -> {

            String username = "admin";

            // Verificar si ya existe
            if (usuarioServicio.buscarUsuarioPorUsername(username).isEmpty()) {

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword("admin123"); // ⚠️ luego encriptamos
                admin.setRol("ADMIN");

                usuarioServicio.guardarUsuario(admin);

                System.out.println("✅ Usuario admin creado");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe");
            }
        };
    }
}
