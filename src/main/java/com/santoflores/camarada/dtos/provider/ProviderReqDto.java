package com.santoflores.camarada.dtos.provider;

import java.util.List;

import com.santoflores.camarada.enums.Plan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record ProviderReqDto(
    @NotBlank 
    String cpfCnpj,

    @NotNull
    List<Long> serviceIds,

    String bio,

    Short experience

) {
}