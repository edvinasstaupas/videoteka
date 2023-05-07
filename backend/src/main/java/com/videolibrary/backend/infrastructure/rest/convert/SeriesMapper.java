package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.Series;
import com.videolibrary.backend.infrastructure.rest.dto.SeriesDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface SeriesMapper {

    SeriesDto map(Series series);

}
