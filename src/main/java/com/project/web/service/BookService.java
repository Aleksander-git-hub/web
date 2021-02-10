package com.project.web.service;

import com.project.web.dto.AuthorDto;
import com.project.web.dto.BookDto;
import com.project.web.entity.AuthorEntity;
import com.project.web.entity.BookEntity;
import com.project.web.exceptions.NotFoundException;
import com.project.web.mapper.BookMapper;
import com.project.web.repository.AuthorRepository;
import com.project.web.repository.BookRepository;
import com.project.web.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookMapper bookMapper;

    public BookEntity saveBook(BookDto bookDto, Long userId) {
        if (StringUtils.isEmpty(bookDto.getTitle()) ||
                bookDto.getPrice() == null) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    bookDto.setUser(user);
                    return bookRepository.save(bookMapper.toEntity(bookDto));
                }).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public List<BookEntity> getAllBooksByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        return bookRepository.findAllByUserId(userId);
    }

    public ResponseEntity<?> deleteBookById(Long userId, Long bookId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        bookRepository.findById(bookId)
                .map(bookEntity -> {
                    bookRepository.delete(bookEntity);
                    bookEntity.setDeleted(true);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
        return ResponseEntity.notFound().build();
    }

    public BookEntity updateBookById(Long userId, Long bookId, BookDto bookDto) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        return bookRepository.findById(bookId)
                .map(bookEntity -> {
                    bookEntity.setTitle(bookDto.getTitle());
                    bookEntity.setPrice(bookDto.getPrice());
                    return bookRepository.save(bookEntity);
                }).orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
    }

    public List<BookEntity> getAllBooksByAuthor(Long authorId, AuthorDto authorDto) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }
        return bookRepository.findAll().
                stream().filter(bookEntity -> authorDto.getId().equals(authorId))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addBookToAuthor(Long authorId, Long bookId, AuthorDto authorDto) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }


    }

}
