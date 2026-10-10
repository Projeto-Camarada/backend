package com.santoflores.camarada.mappers;

public interface Mapper<M, Q, S> {
    M toModel(Q dto);
    
    S fromModel(M model);
}
