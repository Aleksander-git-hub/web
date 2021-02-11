package com.project.web.service;

import com.project.web.dto.UserDto;
import com.project.web.entity.BookEntity;
import com.project.web.entity.User;
import com.project.web.exceptions.NotFoundException;
import com.project.web.mapper.UserMapper;
import com.project.web.repository.BookRepository;
import com.project.web.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(UserDto userDto) {
        if (StringUtils.isEmpty(userDto.getFirstName()) ||
                StringUtils.isEmpty(userDto.getSecondName()) ||
                StringUtils.isEmpty(userDto.getEmail())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        return userRepository.save(userMapper.toEntity(userDto));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
    }

    public User updateUserById(UserDto userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        if (StringUtils.isEmpty(userDto.getFirstName()) ||
            StringUtils.isEmpty(userDto.getSecondName()) ||
            StringUtils.isEmpty(userDto.getEmail())) {
            throw new NotFoundException("Fields are empty!Please, check this!");
        } else {
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setSecondName(userDto.getSecondName());
            existingUser.setAge(userDto.getAge());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setBooks(userDto.getBooks());
        }
        return userRepository.save(existingUser);
    }

    public User addBookToUser(Long userId, Long bookId) {
        if (StringUtils.isEmpty(bookRepository.getById(bookId).getTitle())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        User user = getUserById(userId);
        BookEntity book = bookService.getBookById(bookId);
        user.addBook(book);
        book.setUser(user);
        return user;
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
}
