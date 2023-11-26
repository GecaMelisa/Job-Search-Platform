package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void shouldReturnAllJobs() {
        List<Job> jobs = jobRepository.findAllCustom();

        assertFalse(jobs.isEmpty());
        assertEquals(2, jobs.size());
    }

    @Test
    public void shouldFindJobByPosition() {
        Optional<Job> job = jobRepository.findByPosition("Full-stack developer");

        Assertions.assertTrue(job.isPresent());
        Assertions.assertEquals("Full-stack developer", job.get().getPosition());
    }
}
