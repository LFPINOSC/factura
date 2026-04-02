package com.sistema.factura.Controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sistema.factura.Seguridad.JwtUtil;
import com.sistema.factura.dto.LoginRequest;

@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Map<String, String> login(@RequestBody LoginRequest request){

        if("admin".equals(request.getUsername()) && "password".equals(request.getPassword())){

            String token = jwtUtil.generarToken(request.getUsername());

            return Map.of("token", token);

        } else {

            throw new RuntimeException("Credenciales inválidas");

        }
    }
}