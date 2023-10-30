package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.rest.dto.JobDTO;
import ba.edu.ibu.job.search.platform.rest.dto.JobRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class JobService {

    private final JobRepository jobRepository;

    /**
     * Dependency injection.
     */
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Get all jobs
     */
    public List<JobDTO> getJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs
                .stream()
                .map(JobDTO::new)
                .collect(toList());
    }

    /**
     * Get a job by id
     */
    public JobDTO getJobById(String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        return new JobDTO(job.get());
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

    /**
     * Add a job
     */
    public JobDTO addJob(JobRequestDTO payload) {
        Job job = jobRepository.save(payload.toEntity());
        return new JobDTO(job);
    }

    /**
     * Update a job by id
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

    /**Delete a job*/
    public void deleteJob(String id) {
        Optional<Job> job = jobRepository.findById(id);
        job.ifPresent(jobRepository::delete);
    }

}
