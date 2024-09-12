package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.model.*;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import ba.edu.ibu.job.search.platform.core.repository.ApplicationRepository;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationServiceTest {
    @MockBean
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationService applicationService;
    JobService jobService;



    @Test
    public void shouldReturnApplicationWhenCreated() {

        Application application = new Application();
        User user = new User();
        List<Job> jobs = Arrays.asList(new Job());


        application.setId("applicationId");
        application.setUser(user);
        application.setCv("TestCV");
        application.setApplicationDate("applicationDate");
        application.setJob(new Job());

        Mockito.when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(application);

    }
    @Test
    public void shouldDeleteApplication() {
        Application application = new Application();
        application.setId("applicationId");

        Mockito.when(applicationRepository.findById("applicationId")).thenReturn(Optional.of(application));
        Mockito.doNothing().when(applicationRepository).delete(application);

        applicationService.deleteApplication("applicationId");

        Mockito.verify(applicationRepository, Mockito.times(1)).delete(application);
    }

}