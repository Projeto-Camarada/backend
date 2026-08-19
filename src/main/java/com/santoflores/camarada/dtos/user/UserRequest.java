package com.santoflores.camarada.dtos.user;

import lombok.Data;

public record UserRequest(
    String name,

    String phone,

    String email,

    String password,

    String photoUrl

) {
}