package com.sistema.factura.Seguridad;

import java.security.Key;

import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String secret= "mi_clave_secreta_para_jwt_2026";
    private static final long expiration= 3600000; 
    private static final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public static String generateToken(String username, String role){
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .signWith(key)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + expiration))
                .compact();
    }
    public static String validarToken(String token){
        try {
            var claims = io.jsonwebtoken.Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
