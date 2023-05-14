package com.videolibrary.backend.infrastructure.rest.convert;

import com.videolibrary.backend.domain.entity.User;
import com.videolibrary.backend.infrastructure.rest.dto.CreateUpdateUserDto;
import com.videolibrary.backend.infrastructure.rest.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface UserMapper {
    UserDto map(User user);

    User map(CreateUpdateUserDto dto);
}
