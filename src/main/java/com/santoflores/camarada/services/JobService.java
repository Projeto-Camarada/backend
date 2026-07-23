package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.Job;
import com.santoflores.camarada.repositories.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository repository;

    public Job save(Job job){
        return repository.save(job);
    }

    public Job findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado."));
    }

    public List<Job> findAll(){
        return repository.findAll();
    }

    public List<Job> findByClient(Long clientId){
        return repository.findByClientId(clientId);
    }

    public List<Job> findByProvider(Long providerId){
        return repository.findByProviderUserId(providerId);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
