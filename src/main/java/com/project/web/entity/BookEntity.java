package com.project.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private Boolean deleted = false;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors;

    public void addAuthor(AuthorEntity authorEntity) {
        authors.add(authorEntity);
        authorEntity.getBooks().add(this);
    }

    public void removeAuthor(AuthorEntity authorEntity) {
        authors.remove(authorEntity);
        authorEntity.getBooks().remove(this);
    }
}
