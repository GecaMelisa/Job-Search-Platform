package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.junit.jupiter.api.AfterEach;
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
    private Job testJob;
    private User testUser;

    @BeforeEach
    public void setUp() {
        applicationRepository.deleteAll();

        testUser = new User();
        testJob = new Job();

        testApplication = new Application(
                testUser,
                testJob,
                "sample_cv.pdf",
                "Bachelor's Degree",
                "5 years",
                "2024-08-26"
        );

        applicationRepository.save(testApplication);
    }

    @AfterEach
    public void tearDown() {
        applicationRepository.deleteAll();
    }

    @Test
    public void shouldFindApplicationById() {
        Optional<Application> application = applicationRepository.findById(testApplication.getId());

        Assertions.assertTrue(application.isPresent());
        Assertions.assertEquals(testApplication.getCv(), application.get().getCv());
    }

    @Test
    public void shouldFindApplicationsByJobId() {
        List<Application> applications = applicationRepository.findApplicationsByJobId(testJob.getId());

        Assertions.assertFalse(applications.isEmpty());
        Assertions.assertEquals(1, applications.size());
        Assertions.assertEquals(testApplication.getCv(), applications.get(0).getCv());
    }

    @Test
    public void shouldFindApplicationsByUserId() {
        List<Application> applications = applicationRepository.findByUserId(testUser.getId());

        Assertions.assertFalse(applications.isEmpty());
        Assertions.assertEquals(1, applications.size());
        Assertions.assertEquals(testApplication.getCv(), applications.get(0).getCv());
    }

    @Test
    public void shouldFindApplicationsByJob() {
        List<Application> applications = applicationRepository.findByJob(testJob);

        Assertions.assertFalse(applications.isEmpty());
        Assertions.assertEquals(1, applications.size());
        Assertions.assertEquals(testApplication.getCv(), applications.get(0).getCv());
    }

}
