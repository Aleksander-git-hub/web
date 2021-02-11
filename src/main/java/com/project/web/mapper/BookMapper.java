package com.project.web.mapper;

import com.project.web.dto.BookDto;
import com.project.web.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    BookEntity toEntity(BookDto bookDto);

    BookDto toDto(BookEntity bookEntity);
}
