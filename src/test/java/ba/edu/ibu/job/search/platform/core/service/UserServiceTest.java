package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest

class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
    void shouldReturnUserById() {
        User user = new User();
        List<Application> applications = new ArrayList<>();

        user.setId("someMongoId");
        user.setFirstName("name1");
        user.setLastName("lastname");
        user.setEmail("tes-/gmail.com");
        user.setDateOfBirth("22.11.2002");
        user.setAddress("Sarajevo");
        user.setUserType(UserType.ADMIN);
        user.setEducation("IBU");
        user.setWorkExperience("YES");
        user.setPhoneNumber("061-555-111");
        user.setUsername("testUsername");
        user.setPassword("pass22");
        user.setApplications(applications);

        Mockito.when(userRepository.findById("someMongoId")).thenReturn(Optional.of(user));


        User foundUser = userRepository.getUserById("someMongoId");
        Assertions.assertThat(foundUser.getFirstName()).isEqualTo("name1");
    }

}
