package com.project.web.service;

import com.project.web.dto.AuthorDto;
import com.project.web.dto.BookDto;
import com.project.web.entity.BookEntity;
import com.project.web.entity.User;
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

    public BookEntity saveBook(BookDto bookDto) {
        if (StringUtils.isEmpty(bookDto.getTitle()) ||
            bookDto.getPrice() == null) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        return bookRepository.save(bookMapper.toEntity(bookDto));
    }

    public BookEntity getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity updateBookById(Long bookId, BookDto bookDto) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        if (StringUtils.isEmpty(bookDto.getTitle()) ||
                bookDto.getPrice() == null) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        BookEntity book = bookRepository.getById(bookId);
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        return bookRepository.save(book);
    }

    public ResponseEntity<?> deleteBookById(Long bookId) {
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
        existingBook.setDeleted(true);
        bookRepository.save(existingBook);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> addBookToUser(Long userId, Long bookId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        if (StringUtils.isEmpty(bookRepository.getById(bookId).getTitle())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        User user = userRepository.getById(userId);
        BookEntity book = bookRepository.getById(bookId);
        user.addBook(book);
        book.setUser(user);
        return ResponseEntity.ok().build();
    }

    public List<BookEntity> getAllBooksByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        return bookRepository.findAllByUserId(userId);
    }

    public ResponseEntity<?> removeBookFromUser(Long userId, Long bookId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
        User user = userRepository.getById(userId);
        BookEntity book = bookRepository.getById(bookId);
        user.removeBook(book);
        return ResponseEntity.ok().build();
    }

    public List<BookEntity> getAllBooksByAuthor(Long authorId, AuthorDto authorDto) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }
        return bookRepository.findAll().
                stream().filter(bookEntity -> authorDto.getId().equals(authorId))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addBookToAuthor(Long authorId, Long bookId) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }
        bookRepository.getById(bookId).addAuthor(authorRepository.getById(authorId));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> removeBookFromAuthor(Long authorId, Long bookId) {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException("Author not found with id: " + authorId);
        }
        bookRepository.getById(bookId).removeAuthor(authorRepository.getById(authorId));
        return ResponseEntity.ok().build();
    }
}