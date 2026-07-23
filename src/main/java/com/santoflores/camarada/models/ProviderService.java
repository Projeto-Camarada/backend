package com.santoflores.camarada.models;

import com.santoflores.camarada.ids.ProviderServiceId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "provider_services")
public class ProviderService {

    @EmbeddedId
    private ProviderServiceId id;

    @MapsId("providerId")
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @MapsId("serviceId")
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private String description;
}
