package com.richieoscar.agrologistics.mapper;

public interface Mapper<D,E>{

    D mapToDto(E source);
    E mapToEntity(D source);

}
