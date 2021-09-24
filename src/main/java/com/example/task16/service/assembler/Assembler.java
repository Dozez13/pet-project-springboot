package com.example.task16.service.assembler;


public interface Assembler<E,D >{
    D mergeAggregateIntoDto(E entity);
    E mergeDtoIntoAggregate(D dto);
}
