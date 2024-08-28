package ba.edu.ibu.job.search.platform.core.repository;
import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    private Application testApplication;

    @BeforeEach
    public void setUp() {
        User user = new User();
        Job job = new Job();

        testApplication = new Application(
                user,
                job,
                "sample_cv.pdf",
                "Bachelor's Degree",
                "5 years",
                "2024-08-26"
        );

        applicationRepository.save(testApplication);
    }

    @Test
    public void shouldFindApplicationById() {
        Optional<Application> application = applicationRepository.findById(testApplication.getId());

        Assertions.assertTrue(application.isPresent());
        Assertions.assertEquals(testApplication.getCv(), application.get().getCv());
    }
}
