package com.project.web.mapper;

import com.project.web.dto.UserDto;
import com.project.web.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
