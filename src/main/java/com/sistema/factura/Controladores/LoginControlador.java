package com.sistema.factura.Controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.factura.DTO.LoginRequest;
import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Seguridad.JwtUtil;
import com.sistema.factura.Servicios.UsuarioServicio;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor

public class LoginControlador {
    @Autowired
    private final UsuarioServicio usuarioServicio;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    

    @PostMapping
    public Map<String, String> login(@RequestBody LoginRequest request) {
        System.out.println("🔐 Intento de login para usuario: " + request.getUsername());
        Usuario usuario = usuarioServicio.fyUsuarioUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("Password enviada: " + request.getPassword());
        System.out.println("Password BD: " + usuario.getPassword());
        System.out.println("Coincide: " + passwordEncoder.matches(request.getPassword(), usuario.getPassword()));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        String token = jwtUtil.generarToken(request.getUsername());

        return Map.of("token", token);
    }
}