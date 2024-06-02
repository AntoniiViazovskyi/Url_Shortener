package com.goit.service;

import com.goit.user.data.User;
import com.goit.user.dto.UserDto;
import com.goit.user.service.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User("testuser", "password");
        userDto = new UserDto(1L, "testuser");
    }

    @Test
    public void testToDto() {
        UserDto mappedUserDto = UserMapper.toDTO(user);

        assertEquals(user.getId(), mappedUserDto.getId());
        assertEquals(user.getUsername(), mappedUserDto.getUsername());
    }

    @Test
    public void testToEntity() {
        User mappedUser = UserMapper.toEntity(userDto);

        assertEquals(userDto.getUsername(), mappedUser.getUsername());
        assertEquals(userDto.getPassword(), mappedUser.getPassword());
    }
}