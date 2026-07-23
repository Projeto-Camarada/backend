package com.santoflores.camarada.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class ProviderServiceId implements Serializable {

    private Long providerId;

    private Long serviceId;
}