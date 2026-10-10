package com.santoflores.camarada.dtos.profession;

import jakarta.validation.constraints.NotNull;

public record ProfessionReqDto(
    @NotNull
    String name,
    Boolean active
) {
    
}
