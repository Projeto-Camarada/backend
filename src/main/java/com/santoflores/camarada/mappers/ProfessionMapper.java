package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.profession.ProfessionRequest;
import com.santoflores.camarada.dtos.profession.ProfessionResponse;
import com.santoflores.camarada.models.Profession;

@Component
public class ProfessionMapper {
    
    public Profession toModel(ProfessionRequest request) {
        return Profession.builder()
            .name(request.name())
            .active(request.active())
            .build();
    }

    public ProfessionResponse toResponse(Profession profession) {
        return new ProfessionResponse(
            profession.getId(),
            profession.getName(),
            profession.getActive()
        );
    }
}
