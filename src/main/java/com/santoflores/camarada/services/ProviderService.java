package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.repositories.ProviderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository repository;

    public Provider save(Provider provider){
        return repository.save(provider);
    }

    public Provider findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado."));
    }

    public List<Provider> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}