package com.santoflores.camarada.dtos.job;

import com.santoflores.camarada.enums.JobStatus;

import lombok.Data;

@Data
public class UpdateJobStatusRequest {

    private JobStatus status;

}
