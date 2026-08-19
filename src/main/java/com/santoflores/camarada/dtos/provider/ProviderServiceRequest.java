package com.santoflores.camarada.dtos.provider;

import lombok.Data;

public record ProviderServiceRequest(
    Long serviceId,
    String description
) {
}