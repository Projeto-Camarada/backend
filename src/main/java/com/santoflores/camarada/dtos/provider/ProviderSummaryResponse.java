package com.santoflores.camarada.dtos.provider;

import lombok.Data;

public record ProviderSummaryResponse(
    Long id,

    String name,

    String photoUrl,

    Boolean verified,

    Double rating

) {
}