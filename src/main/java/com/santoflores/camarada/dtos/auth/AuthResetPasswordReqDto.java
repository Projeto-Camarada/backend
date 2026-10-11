package com.santoflores.camarada.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthResetPasswordReqDto(
    @NotBlank String identifier,
    @NotBlank String cpfCnpj,
    @NotBlank String newPassword
) {}
