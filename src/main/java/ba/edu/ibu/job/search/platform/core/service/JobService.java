package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import ba.edu.ibu.job.search.platform.core.repository.ApplicationRepository;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class JobService {

    private JobRepository jobRepository;
    private CompanyService companyService;
    private CompanyRepository companyRepository;
    private ApplicationRepository applicationRepository;
    private UserService userService;
    private UserRepository userRepository;

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

 public List<String> getTypes() {
     List<String> enumNames = Stream.of(JobType.values())
             .map(JobType::name)
             .collect(Collectors.toList());
     return enumNames;
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
     * Get a job by position
     */
    public JobDTO getJobByPosition(String position) {
        Optional<Job> job = jobRepository.findByPosition(position);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given position does not exist.");
        }
        return new JobDTO(job.get());
    }

    /**PAGINATION */


    public JobPageDTO getJobsWithPagination(Integer offset, Integer limit, String search) {
        List <Job> jobs = jobRepository.findAllJobsWithPaginationAndSearch(offset, limit, search);
        Long count = jobRepository.countSearchedJobs(search);

        List<JobDTO> data = jobs
                .stream()
                .map(JobDTO::new)
                .collect(toList());

        return new JobPageDTO(count, data);
    }
    public JobPageDTO getJobsWithPaginationAndFilteringg(Integer offset, Integer limit, JobType jobType) {
        List <Job> jobs = jobRepository.findAllJobsWithPaginationAndFiltering(offset, limit, jobType);
        Long count = jobRepository.countFilteredJobs(jobType);

        List<JobDTO> data = jobs
                .stream()
                .map(JobDTO::new)
                .collect(toList());

        return new JobPageDTO(count, data);
    }

    public JobPageDTO getJobsWithPaginationAndSearchAndFilteringg(Integer offset, Integer limit, String search, JobType jobType) {
        List <Job> jobs = jobRepository.findAllJobsWithPaginationAndSearchAndFiltering(offset, limit, search, jobType);
        Long count = jobRepository.countSearchedAndFilteredJobs(search, jobType);

        List<JobDTO> data = jobs
                .stream()
                .map(JobDTO::new)
                .collect(toList());

        return new JobPageDTO(count, data);
    }


    /**FILTERING */

    public List<JobDTO> getJobsByType(JobType jobType) {
        List<Job> jobs = jobRepository.findByJobType(jobType);

        return jobs.stream()
                .map(JobDTO::new)
                .collect(Collectors.toList());
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
     * Get jobs by company ID
     */
    public List<JobDTO> getJobsByCompanyId(String companyId) {
        // Fetch jobs from repository using companyId
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(JobDTO::new).filter(job -> job.getCompanyId() != null && job.getCompanyId().equals(companyId))
                .collect(Collectors.toList());
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
