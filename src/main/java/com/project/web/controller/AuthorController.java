package com.project.web.controller;

import com.project.web.dto.AuthorDto;
import com.project.web.dto.BookDto;
import com.project.web.entity.AuthorEntity;
import com.project.web.mapper.AuthorMapper;
import com.project.web.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorMapper authorMapper;

    @GetMapping(value = "/book/{bookId}/authors")
    public List<AuthorDto> getAllAuthorsByBook(@PathVariable(name = "bookId") Long bookId,
                                               @RequestBody BookDto bookDto) {
        List<AuthorEntity> authors = authorService.getAllAuthorsByBook(bookId, bookDto);
        return authors.stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

//    @PutMapping(value = "/book/{bookId}/author/{authorId}")
//    public ResponseEntity<?> addAuthorToBook(@PathVariable(name = "bookId") Long bookId,
//                                             @PathVariable(name = "authorId") Long authorId) {
//        return authorService.addAuthorToBook(bookId, authorId);
//    }
//
//    @DeleteMapping(value = "/book/{bookId}/author/{authorId}")
//    public ResponseEntity<?> removeAuthorFromBook(@PathVariable(name = "bookId") Long bookId,
//                                                  @PathVariable(name = "authorId") Long authorId) {
//        return authorService.removeAuthorFromBook(bookId, authorId);
//    }

}
