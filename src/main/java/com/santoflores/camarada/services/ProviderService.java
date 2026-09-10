package com.santoflores.camarada.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santoflores.camarada.enums.Plan;
import com.santoflores.camarada.models.Provider;
import com.santoflores.camarada.models.User;
import com.santoflores.camarada.repositories.ProviderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository repository;
    private final ProviderProfessionService providerProfessionService;
    private final UserService userService;

    public Provider save(Provider provider, Long userId, List<Long> professionIds){

        User user = userService.findById(userId);

        // if (provider.getPlan() == Plan.PREMIUM) {
        //     provider.setPlanExpiresAt(
        //         LocalDateTime.now().plusDays(30)
        //     );
        // }

        provider.setPlan(Plan.FREE);
        provider.setUser(user);

        Provider savedProvider = repository.save(provider);

        providerProfessionService.save(
            savedProvider.getUserId(),
            professionIds
        );

        return savedProvider;
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