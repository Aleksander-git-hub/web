package com.project.web.dto;

import lombok.*;

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
}
