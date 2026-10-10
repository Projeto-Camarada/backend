package com.santoflores.camarada.repositories;

import com.santoflores.camarada.models.ServiceInterest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceInterestRepository extends JpaRepository<ServiceInterest, Long> {
    List<ServiceInterest> findByRequest_Id(Long requestId);
    List<ServiceInterest> findByProvider_Id(Long providerId);
}