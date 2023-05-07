package com.videolibrary.backend.infrastructure.rest.convert;

public interface IMapper <R, D> {
    D map(R entity);
}
