package com.santoflores.camarada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santoflores.camarada.models.JobImage;

public interface JobImageRepository extends JpaRepository<JobImage, Long> {

    List<JobImage> findByJobId(Long jobId);

}