package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.JobStatus;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JobTest {

    @Test
    void shouldCreateNewJob() {
        Company company = new Company();

        // Kreiranje objekta Job sa tačnim konstruktorskim pozivom
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
                JobStatus.ACTIVE,
                "Senior"
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
        Assertions.assertEquals("Senior", job.getSeniority());
    }

    @Test
    void shouldUpdateJobDetails() {
        Company company = new Company();
        Job job = new Job();

        job.setId("newJobId");
        job.setCompany(company);
        job.setPosition("Updated Position");
        job.setDescription("Updated Description");
        job.setLocation("Updated Location");
        job.setJobType(JobType.PART_TIME);
        job.setSalary(3000);
        job.setRequirements(Arrays.asList("newRequirement1", "newRequirement2"));
        job.setPostedDate("01.02.2024.");
        job.setDeadline("newDeadlineDate");
        job.setSeniority("Junior");

        Assertions.assertEquals("newJobId", job.getId());
        Assertions.assertEquals(company, job.getCompany());
        Assertions.assertEquals("Updated Position", job.getPosition());
        Assertions.assertEquals("Updated Description", job.getDescription());
        Assertions.assertEquals("Updated Location", job.getLocation());
        Assertions.assertEquals(JobType.PART_TIME, job.getJobType());
        Assertions.assertEquals(3000, job.getSalary());
        Assertions.assertEquals(Arrays.asList("newRequirement1", "newRequirement2"), job.getRequirements());
        Assertions.assertEquals("01.02.2024.", job.getPostedDate());
        Assertions.assertEquals("newDeadlineDate", job.getDeadline());
        Assertions.assertEquals("Junior", job.getSeniority());
    }

    @Test
    void shouldHandleEmptyJobFields() {
        Job job = new Job();

        Assertions.assertNull(job.getId());
        Assertions.assertNull(job.getCompany());
        Assertions.assertNull(job.getPosition());
        Assertions.assertNull(job.getDescription());
        Assertions.assertNull(job.getLocation());
        Assertions.assertNull(job.getJobType());
        Assertions.assertEquals(0, job.getSalary());
        Assertions.assertNull(job.getRequirements());
        Assertions.assertNull(job.getPostedDate());
        Assertions.assertNull(job.getDeadline());
        Assertions.assertNull(job.getSeniority());
    }
}
