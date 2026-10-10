package com.santoflores.camarada.repositories;

import com.santoflores.camarada.models.ServiceRequestInterests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequestInterests, Long> {
    List<ServiceRequestInterests> findByRequest_Id(Long requestId);
}