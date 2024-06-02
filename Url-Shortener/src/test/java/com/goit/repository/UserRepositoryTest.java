package com.goit.repository;

import com.goit.user.data.User;
import com.goit.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User("testuser", "Password123");
        userRepository.save(user);
        Optional<User> foundUserOptional = userRepository.findByUsername("testuser");
        assertTrue(foundUserOptional.isPresent());
        assertEquals("testuser", foundUserOptional.get().getUsername());
    }
}