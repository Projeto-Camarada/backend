package com.santoflores.camarada.mappers;

import org.springframework.stereotype.Component;

import com.santoflores.camarada.dtos.profession.ProfessionReqDto;
import com.santoflores.camarada.dtos.profession.ProfessionResDto;
import com.santoflores.camarada.models.Profession;

@Component
public class ProfessionMapper {
    
    public Profession toModel(ProfessionReqDto request) {
        return Profession.builder()
            .name(request.name())
            .active(request.active())
            .build();
    }

    public ProfessionResDto toResponse(Profession profession) {
        return new ProfessionResDto(
            profession.getId(),
            profession.getName(),
            profession.getActive()
        );
    }
}
