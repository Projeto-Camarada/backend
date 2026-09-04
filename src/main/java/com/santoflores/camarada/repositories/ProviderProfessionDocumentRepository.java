package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.ProviderProfessionDocument;


public interface ProviderProfessionDocumentRepository extends JpaRepository<ProviderProfessionDocument, Long> {

    List<ProviderProfessionDocument> findByProviderProfessionProviderUserId(Long providerId);

}