package com.project.web.mapper;

import com.project.web.dto.AuthorDto;
import com.project.web.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorEntity toEntity(AuthorDto authorDto);

    AuthorDto toDto(AuthorEntity authorEntity);

}
