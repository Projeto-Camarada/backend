package com.santoflores.camarada.mappers;

public interface Mapper<T, P> {
    T toModel(P dto);
    
    P fromModel(T model);
}
