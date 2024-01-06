package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class JobService {

    private JobRepository jobRepository;
    private CompanyService companyService;
    private CompanyRepository companyRepository;

    /**
     * Dependency injection.
     */
    public JobService(JobRepository jobRepository, CompanyService companyService, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    /**
     * Get all jobs - permitAll
     */
    public List<JobDTO> getJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs
                .stream()
                .map(JobDTO::new)
                .collect(toList());
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
     * Get a job by id
     */
    public Job getJobById2(String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        return job.get();
    }

    /**
     * need this for assigning application to job
     */
    public Job getSubmittedApplications(String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job owner with the given ID does not exist.");
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
     * Add a job - only companyOwner
*/
    public JobDTO addJob(JobRequestDTO payload) {
        String companyId = payload.getCompanyId();

        Company company = companyService.getCompanyById2(companyId);
        Job job = payload.toEntity();
        job.setCompany(company);
        //jobRepository.save(payload.toEntity());
        jobRepository.save(job);

        return new JobDTO(job);
    }



/* //Pokušaj povezivanja job-a sa company
    public void addJob(String jobId, String companyId){
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty()){
            throw new ResourceNotFoundException("The company with the given ID does not exist.");
        }

        Job job = getJobById2(jobId);
        List<Job> jobs = company.get().getJobs();
        jobs.add(job);
        company.get().setJobs(jobs);

        companyRepository.save(company.get());
    }
*/


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
