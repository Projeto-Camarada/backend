package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.ids.ProviderProfessionId;
import com.santoflores.camarada.models.ProviderProfession;


public interface ProviderProfessionRepository extends JpaRepository<ProviderProfession, ProviderProfessionId> {

    List<ProviderProfession> findByProviderUserId(Long providerId);

    List<ProviderProfession> findByProfessionId(Long professionId);

}