package com.santoflores.camarada.mappers;

import com.santoflores.camarada.dtos.job.JobResponse;
import com.santoflores.camarada.models.Job;

public class JobMapper {

    public JobResponse toResponse(Job job) {
        return new JobResponse(
            job.getId(),
            job.getTitle(),
            job.getDescription(),
            job.getStatus(),
            job.getEstimatedPrice(),
            job.getFinalPrice(),
            job.getRequestedAt(),
            job.getActualDurationHours(),
            job.getEstimatedDurationHours(),
            job.getStartedAt(),
            job.getCompletedAt()
        );
    }

}
