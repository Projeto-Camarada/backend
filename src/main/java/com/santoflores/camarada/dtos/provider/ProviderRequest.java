package com.santoflores.camarada.dtos.provider;

import com.santoflores.camarada.enums.Plan;

import lombok.Data;

public record ProviderRequest(
    String cpfCnpj,

    String bio,

    Short experience

) {
}