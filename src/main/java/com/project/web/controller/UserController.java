package com.project.web.controller;

import com.project.web.dto.UserDto;
import com.project.web.entity.User;
import com.project.web.mapper.UserMapper;
import com.project.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/user")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.saveUser(userDto));
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @GetMapping(value = "/users")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser
            (@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user/{id}")
    public UserDto updateUserById
            (@RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.updateUserById(userDto, id));
    }
}
