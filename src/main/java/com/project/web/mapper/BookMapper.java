package com.project.web.mapper;

import com.project.web.dto.BookDto;
import com.project.web.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity toEntity(BookDto bookDto);

    BookDto toDto(BookEntity bookEntity);
}
