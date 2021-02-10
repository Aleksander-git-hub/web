package com.project.web.mapper;

import com.project.web.dto.AuthorDto;
import com.project.web.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    AuthorEntity toEntity(AuthorDto authorDto);

    AuthorDto toDto(AuthorEntity authorEntity);

}
