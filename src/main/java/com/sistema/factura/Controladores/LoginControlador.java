package com.sistema.factura.Controladores;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.factura.Seguridad.JwtUtil;
import com.sistema.factura.dto.LoginRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/login")
public class LoginControlador {
    @PostMapping
    public Map<String, String> login(@RequestBody LoginRequest request){
       if("admin".equals(request.getRol()) && "password".equals(request.getPassword())){
            String token = JwtUtil.generateToken(request.getUsername(), request.getRol());
            return Map.of("token", token);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
