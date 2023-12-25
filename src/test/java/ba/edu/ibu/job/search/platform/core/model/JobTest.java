package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.JobStatus;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class JobTest {

    @Test
    void shouldCreateNewJob() {
        Company company = new Company();

        // Kreiranje objekta Job
        Job job = new Job(
                "someId",
                company,
                "Some Position",
                "Some Description",
                "Some Location",
                JobType.FULL_TIME,
                2000,
                Arrays.asList("requirement1", "requirement2"),
                "25.01.2024.",
                "deadlineDate",
                JobStatus.ACTIVE
        );

        Assertions.assertEquals("someId", job.getId());
        Assertions.assertEquals(company, job.getCompany());
        Assertions.assertEquals("Some Position", job.getPosition());
        Assertions.assertEquals("Some Description", job.getDescription());
        Assertions.assertEquals("Some Location", job.getLocation());
        Assertions.assertEquals(JobType.FULL_TIME, job.getJobType());
        Assertions.assertEquals(2000, job.getSalary());
        Assertions.assertEquals(Arrays.asList("requirement1", "requirement2"), job.getRequirements());
        Assertions.assertEquals("25.01.2024.", job.getPostedDate());
        Assertions.assertEquals("deadlineDate", job.getDeadline());
        Assertions.assertEquals(JobStatus.ACTIVE, job.getJobStatus());
    }
}
