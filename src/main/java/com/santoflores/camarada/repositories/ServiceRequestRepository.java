package com.santoflores.camarada.repositories;

import com.santoflores.camarada.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}