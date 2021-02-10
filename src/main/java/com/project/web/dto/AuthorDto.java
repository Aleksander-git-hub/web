package com.project.web.dto;

import com.project.web.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String secondName;
    private Boolean deleted = false;
    private List<BookEntity> books;
}
