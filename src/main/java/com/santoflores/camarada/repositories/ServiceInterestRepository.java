package com.santoflores.camarada.repositories;

import com.santoflores.camarada.models.ServiceInterest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceInterestRepository extends JpaRepository<ServiceInterest, Long> {
    List<ServiceInterest> findByServiceRequest_Id(Long requestId);
    List<ServiceInterest> findByProvider_UserId(Long providerId);
}