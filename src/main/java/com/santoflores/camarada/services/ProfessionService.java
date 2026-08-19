package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.models.Profession;
import com.santoflores.camarada.repositories.ProfessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessionService {

    private final ProfessionRepository repository;

    public Profession save(Profession job){
        return repository.save(job);
    }

    public Profession findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado."));
    }

    public List<Profession> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
