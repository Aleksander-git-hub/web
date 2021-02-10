package com.project.web.controller;

import com.project.web.dto.AuthorDto;
import com.project.web.dto.BookDto;
import com.project.web.entity.BookEntity;
import com.project.web.mapper.BookMapper;
import com.project.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping(value = "/user/{userId}/book")
    public BookDto saveBook(@PathVariable(value = "userId") Long userID,
                            @RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.saveBook(bookDto, userID));
    }

    @GetMapping(value = "/user/{userId}/books")
    public List<BookDto> getAllBooksByUserId(@PathVariable(name = "userId") Long userId) {
        List<BookEntity> books = bookService.getAllBooksByUserId(userId);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/user/{userId}/book/{bookId}")
    public ResponseEntity<?> deleteBook (@PathVariable(name = "userId") Long userId,
                                         @PathVariable(value = "bookId") Long bookId) {
        return bookService.deleteBookById(userId, bookId);
    }

    @PutMapping(value = "/user/{userId}/book/{bookId}")
    public BookDto updateBookById (@PathVariable(name = "userId") Long userId,
                                   @PathVariable(name = "bookId") Long bookId,
                                   @RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.updateBookById(userId, bookId, bookDto));
    }

    @GetMapping(value = "/author/{authorId}/books")
    public List<BookDto> getAllBooksByAuthor
            (@PathVariable(value = "authorId") Long authorId,
             @RequestBody AuthorDto authorDto) {
        List<BookEntity> books = bookService.getAllBooksByAuthor(authorId, authorDto);
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @PutMapping(value = "/author/{authorId}/book/{bookId}")
    public ResponseEntity<?> addBookToAuthor(@PathVariable(name = "authorId") Long authorId,
                                   @PathVariable(name = "bookId") Long bookId) {
        return bookService.addBookToAuthor(authorId, bookId);
    }

    @DeleteMapping(value = "/author/{authorId}/book/{bookId}")
    public ResponseEntity<?> removeBookFromAuthor(@PathVariable(name = "authorId") Long authorId,
                                                  @PathVariable(name = "bookId") Long bookId) {
        return bookService.removeBookFromAuthor(authorId, bookId);
    }
}
