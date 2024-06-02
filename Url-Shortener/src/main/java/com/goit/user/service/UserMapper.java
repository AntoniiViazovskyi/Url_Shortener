package com.goit.user.service;

import com.goit.user.data.User;
import com.goit.user.dto.UserDto;

public class UserMapper {
    public static UserDto toDTO(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

    public static User toEntity(UserDto userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword());
    }
}
