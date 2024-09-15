package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // Create a User instance
        user = new User(
                "userId",
                UserType.GUEST,
                "John",
                "Doe",
                "1980-01-01",
                "john.doe@example.com",
                "123456789",
                "123 Street",
                "Bachelor's",
                "5 years experience",
                "john_doe",
                "password",
                "2024-01-01",
                Collections.emptyList()
        );

        // Save the User instance to the repository
        userRepository.save(user);
    }

    @Test
    public void shouldFindUserByEmailCustom() {
        Optional<User> userOpt = userRepository.findByEmailCustom("john.doe@example.com");

        assertTrue(userOpt.isPresent());
        User foundUser = userOpt.get();
        assertEquals("john.doe@example.com", foundUser.getEmail());
        assertEquals("John", foundUser.getFirstName());
    }

    @Test
    public void shouldFindUserByEmail() {
        Optional<User> userOpt = userRepository.findByEmail("john.doe@example.com");

        assertTrue(userOpt.isPresent());
        User foundUser = userOpt.get();
        assertEquals("john.doe@example.com", foundUser.getEmail());
    }

    @Test
    public void shouldFindUserByUsernameOrEmail() {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail("john.doe@example.com");

        assertTrue(userOpt.isPresent());
        User foundUser = userOpt.get();
        assertEquals("john.doe@example.com", foundUser.getEmail());
    }

    @Test
    public void shouldFindUserByUsername() {
        User foundUser = userRepository.findByUsername("john_doe");

        assertNotNull(foundUser);
        assertEquals("john_doe", foundUser.getUsername());
    }

    @Test
    public void shouldGetUserById() {
        User foundUser = userRepository.getUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void shouldReturnEmptyForInvalidEmail() {
        Optional<User> userOpt = userRepository.findByEmail("invalid@example.com");

        assertFalse(userOpt.isPresent());
    }

    @Test
    public void shouldReturnEmptyForInvalidUsername() {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail("invalid_username");

        assertFalse(userOpt.isPresent());
    }
}
