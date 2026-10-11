package com.santoflores.camarada.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordVerifyReqDto(
    @NotBlank  
    String phone,
    @NotBlank 
    String cpfCnpj
) {}
