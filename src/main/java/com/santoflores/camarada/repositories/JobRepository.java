package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByClientId(Long clientId);

    List<Job> findByProviderUserId(Long providerId);

}