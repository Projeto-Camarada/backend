package com.santoflores.camarada.repositories;

import com.santoflores.camarada.models.ServiceRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    public List<ServiceRequest> findByProfessions_Id(Long profession);
    public List<ServiceRequest> findByProfessions_IdIn(List<Long> professions);
}