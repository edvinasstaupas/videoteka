package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.User;
import com.videolibrary.backend.infrastructure.rest.dto.CreateUpdateUserDto;
import com.videolibrary.backend.infrastructure.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DefaultMapperConfig.class)
public interface UserMapper {
    UserDto map(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "history", ignore = true)
    User map(CreateUpdateUserDto dto);
}
