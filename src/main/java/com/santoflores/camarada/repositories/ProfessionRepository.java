package com.santoflores.camarada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}