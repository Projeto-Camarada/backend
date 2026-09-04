package com.santoflores.camarada.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.ids.ProviderProfessionId;
import com.santoflores.camarada.models.Profession;
import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.models.ProviderProfession;
import com.santoflores.camarada.repositories.ProfessionRepository;
import com.santoflores.camarada.repositories.ProviderProfessionRepository;
import com.santoflores.camarada.repositories.ProviderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderProfessionService {

    private final ProviderProfessionRepository repository;
    private final ProviderRepository providerRepository;
    private final ProfessionRepository professionRepository;

    public List<ProviderProfession> save(Long providerId, List<Long> professionIds){

        Provider provider = providerRepository.findById(providerId)
            .orElseThrow(() -> new RuntimeException("Provider não encontrado"));
        
        if (professionIds == null || professionIds.isEmpty()) {
            throw new RuntimeException("Nenhuma profissão informada");
        }
        
        List<Profession> professions = professionRepository.findAllById(professionIds);
        
        if (professions.size() != professionIds.size()) {
            throw new RuntimeException("Uma ou mais profissões não foram encontradas");
        }
        
        List<ProviderProfession> listProviderProfessions = new ArrayList<ProviderProfession>();

        for (Profession profession : professions) {
            ProviderProfession providerProfession = ProviderProfession.builder()
                .id(new ProviderProfessionId(
                    providerId, 
                    profession.getId()
                ))
                .provider(provider)
                .profession(profession)
                .description("")
                .build();

            listProviderProfessions.add(providerProfession);
        }       

        return repository.saveAll(listProviderProfessions);
    }

    public List<ProviderProfession> findByProvider(Long providerId){
        return repository.findByProviderUserId(providerId);
    }

    public List<ProviderProfession> findByProfession(Long professionId){
        return repository.findByProfessionId(professionId);
    }

}
