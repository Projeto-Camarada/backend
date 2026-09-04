package com.santoflores.camarada.models;

import com.santoflores.camarada.ids.ProviderProfessionId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provider_professions")
public class ProviderProfession {

    @EmbeddedId
    private ProviderProfessionId id;

    @MapsId("providerId")
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @MapsId("professionId")
    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    private String description;
}
