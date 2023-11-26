package ba.edu.ibu.job.search.platform.rest.controller;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.service.ApplicationService;
import ba.edu.ibu.job.search.platform.core.service.CompanyOwnerService;
import ba.edu.ibu.job.search.platform.core.service.JwtService;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import ba.edu.ibu.job.search.platform.rest.configuration.SecurityConfiguration;
import ba.edu.ibu.job.search.platform.rest.controllers.UserController;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
@Import(SecurityConfiguration.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    CompanyOwnerService companyOwnerService;

    @MockBean
    JwtService jwtService;

    @MockBean
    AuthenticationProvider authenticationProvider;


    @Test
    void shouldReturnAllUsers()  throws Exception{
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

        Mockito.when(userService.getUsers()).thenReturn(List.of(new UserDTO(user)));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        if (response != null && !response.isEmpty()) {
            Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        } else {
            System.out.println("Response is null or empty!");
        }

        if (response != null && !response.isEmpty()) {
            Assertions.assertEquals("Test Name", JsonPath.read(response, "$.[0].companyName"));
        } else {
            System.out.println("Response is null or empty!");

        }

    }


}
