package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.ProviderService;
import com.santoflores.camarada.ids.ProviderServiceId;

public interface ProviderServiceRepository extends JpaRepository<ProviderService, ProviderServiceId> {

    List<ProviderService> findByProviderUserId(Long providerId);

    List<ProviderService> findByServiceId(Long serviceId);

}