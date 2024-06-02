package com.goit.service;

import com.goit.user.data.User;
import com.goit.user.dto.UserDto;
import com.goit.user.repository.UserRepository;
import com.goit.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14-alpine")
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("mysecretpassword");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setPassword("password");
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void testCreateUser() {
        UserDto createdUser = userService.createUser(userDto, "password");

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());

        User userFromDb = userRepository.findById(createdUser.getId()).orElse(null);
        assertNotNull(userFromDb);
        assertTrue(passwordEncoder.matches("password", userFromDb.getPassword()));
    }

    @Test
    public void testGetUserById() {
        UserDto createdUser = userService.createUser(userDto, "password");

        UserDto foundUser = userService.getUserById(createdUser.getId());

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testGetAllUsers() {
        userService.createUser(userDto, "password");

        List<UserDto> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());
    }

    @Test
    public void testGetUserByUsername() {
        userService.createUser(userDto, "password");

        UserDto foundUser = userService.getUserByUsername("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        UserDto createdUser = userService.createUser(userDto, "password");
        createdUser.setUsername("updatedUser");

        UserDto updatedUser = userService.updateUser(createdUser.getId(), createdUser);

        assertNotNull(updatedUser);
        assertEquals("updatedUser", updatedUser.getUsername());

        User userFromDb = userRepository.findById(updatedUser.getId()).orElse(null);
        assertNotNull(userFromDb);
        assertEquals("updatedUser", userFromDb.getUsername());
    }

    @Test
    public void testDeleteUser() {
        UserDto createdUser = userService.createUser(userDto, "password");

        boolean isDeleted = userService.deleteUser(createdUser.getId());

        assertTrue(isDeleted);
        assertFalse(userRepository.existsById(createdUser.getId()));
    }

    @Test
    public void testExistsByUsername() {
        userService.createUser(userDto, "password");

        boolean exists = userService.existsByUsername("testuser");

        assertTrue(exists);
    }
}