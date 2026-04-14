package com.sistema.factura.Seguridad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Servicios.UsuarioServicio;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initUsuarios(UsuarioServicio usuarioServicio, PasswordEncoder passwordEncoder) {
        return args -> {
            String username = "admin";

            if (usuarioServicio.fyUsuarioUsername(username).isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo("admin@factura.com");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
              

                usuarioServicio.crearUsuario(admin);

                System.out.println("✅ Usuario admin creado correctamente");
            } else {
                System.out.println("ℹ️ El usuario admin ya existe");
            }
        };
    }
}
