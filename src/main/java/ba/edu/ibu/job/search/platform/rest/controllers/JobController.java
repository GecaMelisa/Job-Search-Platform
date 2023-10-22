package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.service.JobService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * Get all jobs
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<JobDTO>>getJobs() {
        return ResponseEntity.ok(jobService.getJobs());
    }

    /**
     * Get a job by ID
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    /**
     * Add a job
     */
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<JobDTO> register(@RequestBody JobRequestDTO job) {
        return ResponseEntity.ok(jobService.addJob(job));
    }

    /**
     * Update a job by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable String id, @RequestBody JobRequestDTO job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    /**
     * Delete a job
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Find a job by title
     */
    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<JobDTO> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(jobService.findByTitle(title));
    }
}

