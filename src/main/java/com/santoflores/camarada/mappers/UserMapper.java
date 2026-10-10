package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.user.UserReqDto;
import com.santoflores.camarada.dtos.user.UserResDto;
import com.santoflores.camarada.models.User;

@Component
public class UserMapper implements Mapper<User, UserReqDto, UserResDto> {
    
    public User toModel(UserReqDto request) {
        return User.builder()
            .name(request.name())
            .phone(request.phone())
            .password(request.password())
            .photoUrl(request.photoUrl())
            .build();
    }

    public UserResDto fromModel(User user) {
        return new UserResDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getPhotoUrl()
        );
    }
}
