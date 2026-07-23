package com.santoflores.camarada.dtos.provider;

import java.time.LocalDateTime;

import com.santoflores.camarada.enums.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProviderResponse {

    private Long id;

    private Plan plan;

    private LocalDateTime planExpiresAt;

    private String cpfCnpj;

    private String name;

    private String photoUrl;

    private String bio;

    private Boolean verified;

    private Short experience;

    // private Double rating;

}