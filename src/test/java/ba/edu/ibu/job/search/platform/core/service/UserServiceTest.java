package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
class sUserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("someMongoId");
        user.setFirstName("name1");
        user.setLastName("lastname");
        user.setEmail("test@gmail.com");
        user.setDateOfBirth("22.11.2002");
        user.setAddress("Sarajevo");
        user.setUserType(UserType.ADMIN);
        user.setEducation("IBU");
        user.setWorkExperience("YES");
        user.setPhoneNumber("061-555-111");
        user.setUsername("testUsername");
        user.setPassword("pass22");
        user.setApplications(new ArrayList<>());
    }

    @Test
    void ShouldReturnAllUsers() {
        User user = new User();
        List<Application> applications = new ArrayList<>();

        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setEmail("test@gmail.com");
        user.setDateOfBirth("22.11.2002");
        user.setAddress("Sarajevo");
        user.setUserType(UserType.ADMIN);
        user.setEducation("IBU");
        user.setWorkExperience("YES");
        user.setPhoneNumber("061-555-111");
        user.setUsername("testUsername");
        user.setPassword("pass22");
        user.setApplications(applications);

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        Assertions.assertThat(user.getFirstName()).isEqualTo("testName");
        Assertions.assertThat(user.getAddress()).isEqualTo("Sarajevo");
        Assertions.assertThat(user.getUserType()).isNotNull();

    }


    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        Mockito.when(userRepository.findById("someMongoId")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getUserById("someMongoId"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The user with the given ID does not exist.");
    }

    @Test
    void shouldReturnUserByEmail() {
        Mockito.when(userRepository.findByEmailCustom("test@gmail.com")).thenReturn(Optional.of(user));

        User foundUser = userService.getUserByEmail("test@gmail.com");

        Assertions.assertThat(foundUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundByEmail() {
        Mockito.when(userRepository.findByEmailCustom("test@gmail.com")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getUserByEmail("test@gmail.com"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The user with the given ID does not exist.");
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setFirstName("updatedName");

        Mockito.when(userRepository.findById("someMongoId")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.updateUser("someMongoId", userRequestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The user with the given ID does not exist.");
    }

    @Test
    void shouldDeleteUser() {
        Mockito.when(userRepository.findById("someMongoId")).thenReturn(Optional.of(user));

        userService.deleteUser("someMongoId");

        Mockito.verify(userRepository).delete(user);
    }

    @Test
    void shouldReturnUserByUsername() {
        Mockito.when(userRepository.findByUsername("testUsername")).thenReturn(user);

        User foundUser = userService.getUserByUsername("testUsername");

        Assertions.assertThat(foundUser.getUsername()).isEqualTo("testUsername");
    }



}
