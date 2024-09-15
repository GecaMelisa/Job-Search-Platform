package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import ba.edu.ibu.job.search.platform.core.model.enums.JobStatus;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.rest.dto.JobDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    private CompanyOwner companyOwner;
    private Company company;
    private Job job;

    @BeforeEach
    public void setUp() {
        // Create a CompanyOwner instance
        companyOwner = new CompanyOwner(
                "ownerId",
                UserType.COMPANY_OWNER,
                "John",
                "Doe",
                "1980-01-01",
                "john.doe@example.com",
                "123456789",
                "123 Street",
                "Bachelor's",
                "5 years experience",
                "john_doe",
                "password",
                "2024-01-01",
                Collections.emptyList(), // Applications list
                "userId" // userId
        );

        // Create a Company instance with the CompanyOwner
        company = new Company(
                "companyId",
                companyOwner,
                "Company One",
                "Address One",
                "123456789",
                "email1@example.com",
                Collections.emptyList(), // List of Jobs
                StatusRequest.APPROVED,
                "Company Description"
        );

        // Create a Job instance with the Company
        job = new Job(
                "jobId",
                company,
                "Full-stack Developer",
                "Develop and maintain web applications",
                "New York",
                JobType.FULL_TIME,
                90000,
                List.of("Java", "Spring Boot", "React"),
                "2024-08-01",
                "2024-12-31",
                JobStatus.ACTIVE,
                "Senior"
        );

        // Save the Job instance to the repository
        jobRepository.save(job);
    }

    @Test
    public void shouldFindJobsByCompanyId() {
        List<Job> jobs = jobRepository.findByCompanyId("companyId");

        assertFalse(jobs.isEmpty());
        assertEquals("companyId", jobs.get(0).getCompanyId());
    }

    @Test
    public void shouldFindJobsByJobType() {
        List<Job> jobs = jobRepository.findByJobType(JobType.FULL_TIME);

        assertFalse(jobs.isEmpty());
        assertEquals(JobType.FULL_TIME, jobs.get(0).getJobType());
    }

    @Test
    public void shouldCountSearchedJobs() {
        Long count = jobRepository.countSearchedJobs("Full-stack Developer");

        assertEquals(1, count);
    }
    @Test
    public void shouldFindJobsWithPaginationAndFiltering() {
        List<Job> jobs = jobRepository.findAllJobsWithPaginationAndFiltering(0, 10, JobType.FULL_TIME);

        assertFalse(jobs.isEmpty());
        assertEquals(JobType.FULL_TIME, jobs.get(0).getJobType());
    }


}
