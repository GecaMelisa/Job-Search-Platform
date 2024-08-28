package ba.edu.ibu.job.search.platform.rest.controller;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.service.ApplicationService;
import ba.edu.ibu.job.search.platform.core.service.JobService;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import ba.edu.ibu.job.search.platform.rest.configuration.SecurityConfiguration;
import ba.edu.ibu.job.search.platform.rest.controllers.ApplicationController;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfiguration.class)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @MockBean
    private JobService jobService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllApplications() throws Exception {
        // Mock data
        User user = new User();
        Job job = new Job();
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        SubmitAppDTO submitAppDTO = new SubmitAppDTO(application);

        Mockito.when(applicationService.getApplications()).thenReturn(Collections.singletonList(submitAppDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/applications/")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].user").exists()) // Assuming you want to check user details
                .andExpect(jsonPath("$[0].job").exists()); // Assuming you want to check job details
    }

    @Test
    void shouldReturnApplicationById() throws Exception {
        // Mock data
        User user = new User();
        Job job = new Job();
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        SubmitAppDTO submitAppDTO = new SubmitAppDTO(application);

        Mockito.when(applicationService.getApplicationById("appId")).thenReturn(submitAppDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/applications/byId/{id}", "appId")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user").exists()) // Assuming you want to check user details
                .andExpect(jsonPath("$.job").exists()); // Assuming you want to check job details
    }

    @Test
    void shouldReturnApplicationByUserId() throws Exception {
        // Mock data
        User user = new User();
        Job job = new Job();
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        SubmitAppDTO submitAppDTO = new SubmitAppDTO(application);

        Mockito.when(applicationService.getApplicationByUserId("userId")).thenReturn(submitAppDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/applications/{userId}", "userId")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user").exists()) // Assuming you want to check user details
                .andExpect(jsonPath("$.job").exists()); // Assuming you want to check job details
    }

    @Test
    void shouldSubmitApplication() throws Exception {
        // Mock data
        SubmitAppRequestDTO submitAppRequestDTO = new SubmitAppRequestDTO();
        submitAppRequestDTO.setJobId("jobId");

        User user = new User();
        Job job = new Job();
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        SubmitAppDTO submitAppDTO = new SubmitAppDTO(application);

        Mockito.when(applicationService.addAppToJob(submitAppRequestDTO)).thenReturn(submitAppDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/applications/submitApp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(submitAppRequestDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user").exists()) // Assuming you want to check user details
                .andExpect(jsonPath("$.job").exists()); // Assuming you want to check job details
    }

    @Test
    void shouldDeleteApplication() throws Exception {
        Mockito.doNothing().when(applicationService).deleteApplication("appId");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/applications/{id}", "appId")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
