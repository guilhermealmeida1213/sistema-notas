package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String secret;

    // Token válido por 1 hora
    private static final long EXPIRACAO = 60 * 60 * 1000;

    public JwtService(
            @Value("${jwt.secret}") String secret
    ) {
        this.secret = secret;
    }

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Gerar token
    public String gerarToken(Usuario usuario) {

        Date agora = new Date();
        Date expiracao = new Date(
                agora.getTime() + EXPIRACAO
        );

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("id", usuario.getIdUsuario())
                .claim("perfil", usuario.getPerfil())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(getChave())
                .compact();
    }

    // Extrair todas as informações do token
    private Claims extrairClaims(String token) {

        return Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extrair e-mail
    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    // Extrair perfil
    public String extrairPerfil(String token) {
        return extrairClaims(token)
                .get("perfil", String.class);
    }

    // Verificar se o token é válido
    public boolean tokenValido(String token) {

        try {

            Claims claims = extrairClaims(token);

            return claims.getExpiration()
                    .after(new Date());

        } catch (Exception e) {
            return false;
        }
    }
}