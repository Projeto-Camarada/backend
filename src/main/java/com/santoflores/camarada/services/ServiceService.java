package com.santoflores.camarada.services;


import java.util.List;

import com.santoflores.camarada.models.Service;
import com.santoflores.camarada.repositories.ServiceRepository;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;

    public Service save(Service service){
        return repository.save(service);
    }

    public Service findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
    }

    public List<Service> findAll(){
        return repository.findAll();
    }

}
