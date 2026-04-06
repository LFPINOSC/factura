package com.sistema.factura.Controladores;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.sistema.factura.Entidades.Usuario;
import com.sistema.factura.Seguridad.JwtUtil;
import com.sistema.factura.Servicios.UsuarioServicio;
import com.sistema.factura.dto.LoginRequest;

@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    private final UsuarioServicio usuarioServicio;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginControlador(UsuarioServicio usuarioServicio,
                            JwtUtil jwtUtil,
                            PasswordEncoder passwordEncoder) {
        this.usuarioServicio = usuarioServicio;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public Map<String, String> login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioServicio.buscarUsuarioPorUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(request.getUsername());

        return Map.of("token", token);
    }
}