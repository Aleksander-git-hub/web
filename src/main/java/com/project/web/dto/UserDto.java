package com.project.web.dto;

import com.project.web.entity.BookEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private Integer age;
    private String email;
    private Boolean deleted = false;
    private List<BookEntity> books = new ArrayList<>();
}
