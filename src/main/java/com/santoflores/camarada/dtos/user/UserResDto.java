package com.santoflores.camarada.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

public record UserResDto(
    Long id,

    String name,

    String email,

    String phone,

    String photoUrl

) {
}