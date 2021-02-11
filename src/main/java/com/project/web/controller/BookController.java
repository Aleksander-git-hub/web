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

    @PostMapping(value = "/book")
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.saveBook(bookDto));
    }

    @GetMapping(value = "/book/{bookId}")
    public BookDto getBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookMapper.toDto(bookService.getBookById(bookId));
    }

    @GetMapping(value = "/books")
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookService.getAllBooks();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @PutMapping(value = "/book/{bookId}")
    public BookDto updateBookById (@PathVariable(name = "bookId") Long bookId,
                                   @RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.updateBookById(bookId, bookDto));
    }

    @DeleteMapping(value = "/book/{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookService.deleteBookById(bookId);
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
