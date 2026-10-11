package com.santoflores.camarada.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginReqDto(
    @NotBlank
    String identifier,

    @NotBlank
    String password

) {
}