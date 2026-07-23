package com.santoflores.camarada.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByName(String name);

}