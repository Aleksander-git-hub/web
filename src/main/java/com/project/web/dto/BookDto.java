package com.project.web.dto;

import com.project.web.entity.AuthorEntity;
import com.project.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Double price;
    private Boolean deleted;
    private UserDto user;
    private List<AuthorDto> authors;
}
