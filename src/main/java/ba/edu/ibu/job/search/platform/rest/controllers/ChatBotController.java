package ba.edu.ibu.job.search.platform.rest.controllers;


import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    @Autowired
    private JobRepository jobRepository;

    // Search jobs by query (position or location)
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String query) {
        return jobRepository.findAllJobsWithPaginationAndSearch(0, 10, query);
    }

    // Get job details by job ID
    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Apply for a job
    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(@RequestBody SubmitAppDTO applicationDTO) {
        Optional<Job> jobOptional = jobRepository.findById(applicationDTO.getJob().getJobId());
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.getSubmittedApplications().add(applicationDTO);
            jobRepository.save(job);
            return ResponseEntity.ok("Application submitted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
    }
}
