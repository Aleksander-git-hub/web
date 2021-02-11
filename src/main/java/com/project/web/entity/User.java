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
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<BookEntity> books = new ArrayList<>();

    public void addBook(BookEntity bookEntity) {
        books.add(bookEntity);
    }

    public void removeBook(BookEntity bookEntity) {
        books.remove(bookEntity);
    }
}
