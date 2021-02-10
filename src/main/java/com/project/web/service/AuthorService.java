package com.project.web.service;

import com.project.web.dto.BookDto;
import com.project.web.entity.AuthorEntity;
import com.project.web.exceptions.NotFoundException;
import com.project.web.repository.AuthorRepository;
import com.project.web.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<AuthorEntity> getAllAuthorsByBook(Long bookId, BookDto bookDto) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        return authorRepository.findAll()
                .stream().filter(authorEntity -> bookDto.getId().equals(bookId))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addAuthorToBook(Long bookId, Long authorId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        bookRepository.getById(bookId).addAuthor(authorRepository.getById(authorId));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> removeAuthorFromBook(Long bookId, Long authorId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        bookRepository.getById(bookId).removeAuthor(authorRepository.getById(authorId));
        return ResponseEntity.ok().build();
    }
}
