package com.santoflores.camarada.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record UserReqDto(
    @NotBlank 
    String name,
    
    @NotBlank 
    String phone,

    String email,

    @NotBlank 
    String password,

    String photoUrl

) {
}