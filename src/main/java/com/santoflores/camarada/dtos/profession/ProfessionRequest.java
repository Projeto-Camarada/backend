package com.santoflores.camarada.dtos.profession;

import jakarta.validation.constraints.NotNull;

public record ProfessionRequest(
    @NotNull
    String name,
    Boolean active
) {
    
}
