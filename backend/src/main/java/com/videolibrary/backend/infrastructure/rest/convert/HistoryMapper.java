package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.HistoryAware;
import com.videolibrary.backend.infrastructure.rest.dto.HistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class, uses = VideoMapper.class)
public interface HistoryMapper {

    @Mapping(source = "id", target = "targetId")
    HistoryDto map(HistoryAware entity);
}
