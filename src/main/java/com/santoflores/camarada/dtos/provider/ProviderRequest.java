package com.santoflores.camarada.dtos.provider;

import java.util.List;

import com.santoflores.camarada.enums.Plan;

import lombok.Data;

public record ProviderRequest(
    String cpfCnpj,

    List<Long> serviceIds,

    String bio,

    Short experience

) {
}