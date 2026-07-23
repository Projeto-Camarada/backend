package com.santoflores.camarada.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.auth.RegisterRequest;
import com.santoflores.camarada.models.User;

@Component
public class AuthMapper {
    
    public User toModel(RegisterRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}
