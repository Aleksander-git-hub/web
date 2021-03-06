package com.project.web.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String email;

    private Boolean deleted = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BookEntity> books = new ArrayList<>();

}
