package com.goit.user.service;

import com.goit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDTO, String rawPassword);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto getUserByUsername(String username);
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
    boolean existsByUsername(String username);
}
