package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/*
@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void shouldReturnAllJobs() {
        List<Job> jobs = jobRepository.findAllCustom();

        Assertions.assertEquals(1, jobs.size());
    }

    @Test
    public void shouldFindJobByPosition() {
        Optional<Job> jobs = jobRepository.findByPosition("Full-stack developer");
        Assertions.assertNotNull(jobs.orElse(null));
    }

}

 */

