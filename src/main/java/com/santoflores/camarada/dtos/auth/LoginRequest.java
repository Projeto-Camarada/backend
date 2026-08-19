package com.santoflores.camarada.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginRequest(
    @NotBlank
    String phone,

    @NotBlank
    String password

) {
}