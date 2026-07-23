package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.user.UserRequest;
import com.santoflores.camarada.dtos.user.UserResponse;
import com.santoflores.camarada.models.User;

@Component
public class UserMapper {
    
    public User toModel(UserRequest request) {
        return User.builder()
            .name(request.getName())
            .phone(request.getPhone())
            .password(request.getPassword())
            .photoUrl(request.getPhotoUrl())
            .build();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getPhotoUrl()
        );
    }
}
