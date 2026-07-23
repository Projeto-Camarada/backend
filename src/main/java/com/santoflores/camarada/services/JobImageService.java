package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.JobImage;
import com.santoflores.camarada.repositories.JobImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobImageService {

    private final JobImageRepository repository;

    public JobImage save(JobImage image){
        return repository.save(image);
    }

    public List<JobImage> findByJob(Long jobId){
        return repository.findByJobId(jobId);
    }

}
