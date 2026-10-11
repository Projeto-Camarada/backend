package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.auth.RegisterRequest;
import com.santoflores.camarada.models.User;

@Component
public class AuthMapper {
    
    public User toModel(RegisterRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();
    }
}
