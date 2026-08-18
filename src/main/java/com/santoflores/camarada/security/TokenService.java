package com.santoflores.camarada.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.santoflores.camarada.models.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generationToken(User user) {
        try {
            String token = JWT.create()
                    .withIssuer("camarada")
                    .withSubject(user.getPhone())
                    .withClaim("userId", user.getId())
                    .withClaim("name", user.getName())
                    .withExpiresAt(getExpirationTokenDate())
                    .sign(Algorithm.HMAC256(secret));
            return token;
        } catch (Exception e) {
            throw new RuntimeException("Erro na criação do token JWT", e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("camarada")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Erro na verificação do token JWT");
        }
    }

    public Long getUserIdFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("camarada")
                    .build()
                    .verify(token)
                    .getClaim("userId").asLong();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar identificado do usuário", e);
        }
    }

    // token com 7 dias de duração até expirar
    private Instant getExpirationTokenDate() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}