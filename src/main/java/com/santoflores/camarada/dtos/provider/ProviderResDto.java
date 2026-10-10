package com.santoflores.camarada.dtos.provider;

import java.time.LocalDateTime;

import com.santoflores.camarada.enums.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

public record ProviderResDto(
    Long id,

    Plan plan,

    LocalDateTime planExpiresAt,

    String cpfCnpj,

    String name,

    String photoUrl,

    String bio,

    Boolean verified,

    Short experience

) {
    // private Double rating;

}