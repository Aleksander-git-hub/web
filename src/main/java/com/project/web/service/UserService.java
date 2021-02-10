package com.project.web.service;

import com.project.web.dto.UserDto;
import com.project.web.entity.User;
import com.project.web.exceptions.NotFoundException;
import com.project.web.mapper.UserMapper;
import com.project.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(UserDto userDto) {
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
        /*if (StringUtils.isEmpty(userDto.getFirstName())) {
            throw new NotFoundException("Fields can not empty!");
        }*/
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setSecondName(userDto.getSecondName());
        existingUser.setAge(userDto.getAge());
        existingUser.setEmail(userDto.getEmail());
        return userRepository.save(existingUser);
    }
}
