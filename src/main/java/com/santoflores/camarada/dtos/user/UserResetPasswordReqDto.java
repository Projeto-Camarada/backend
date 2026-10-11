package com.santoflores.camarada.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserResetPasswordReqDto(
    @NotBlank String newPassword
) {}
