package com.santoflores.camarada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}