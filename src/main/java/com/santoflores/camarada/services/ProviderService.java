package com.santoflores.camarada.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.enums.Plan;
import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.repositories.ProviderRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository repository;
    private final UserService userService;

    public Provider save(Provider provider, Long userId){

        User user = userService.findById(userId);

        // if (provider.getPlan() == Plan.PREMIUM) {
        //     provider.setPlanExpiresAt(
        //         LocalDateTime.now().plusDays(30)
        //     );
        // }

        provider.setPlan(Plan.FREE);
        provider.setUser(user);

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