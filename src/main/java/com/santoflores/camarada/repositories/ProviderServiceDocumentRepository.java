package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.ProviderServiceDocument;

public interface ProviderServiceDocumentRepository extends JpaRepository<ProviderServiceDocument, Long> {

    List<ProviderServiceDocument> findByProviderServiceProviderUserId(Long providerId);

}