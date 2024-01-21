package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.repository.ApplicationRepository;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class JobService {

    private JobRepository jobRepository;
    private CompanyService companyService;
    private CompanyRepository companyRepository;
    private ApplicationRepository applicationRepository;

    /**
     * Dependency injection.
     */
    public JobService(JobRepository jobRepository, CompanyService companyService, ApplicationRepository applicationRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
        this.companyRepository = companyRepository;
        this.applicationRepository=applicationRepository;
    }


    /**
     * Get all jobs - permitAll
     */
    public List<JobDTO> getJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs
                .stream()
                .map(JobDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Get all submitted applications for job
     */

    public List<Application> getAllApplicationsForJob(String jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

        // Dohvati sve aplikacije povezane s tim poslom
        List<Application> applications = applicationRepository.findByJob(job);

        return applications;
    }


    /**
     * Get a job by id - permitAll
     */
    public JobDTO getJobById(String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        return new JobDTO(job.get());
    }

    /**
     * Get a job by id - ne ide preko DTO
     */
    public Job getJobById2(String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        return job.get();
    }


    /**
     * Get a job by position - permitAll
     */
    public JobDTO getJobByPosition(String position) {
        Optional<Job> job = jobRepository.findByPosition(position);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given position does not exist.");
        }
        return new JobDTO(job.get());
    }

    /**
     * Add a job
     * */

    public JobDTO createJob(JobRequestDTO payload) {
        String companyId = payload.getCompanyId();

        Company company = companyService.getCompanyById2(companyId);
        Job job = payload.toEntity();
        job.setCompany(company);
        jobRepository.save(job);

        return new JobDTO(job);
    }

    /**
     * Get job by company
     */
    public List<Job> getJobsByCompany(String companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    /**
     * Update a job by id - companyOwner only
     */
    public JobDTO updateJob(String id, JobRequestDTO payload) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        Job updatedJob = payload.toEntity();
        updatedJob.setId(job.get().getId());
        updatedJob = jobRepository.save(updatedJob);
        return new JobDTO(updatedJob);
    }

    /**
     * Delete a job - only companyOwner
     */
    public void deleteJob(String id) {
        Optional<Job> job = jobRepository.findById(id);
        job.ifPresent(jobRepository::delete);
    }


}
