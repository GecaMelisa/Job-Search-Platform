package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.service.JobService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@SecurityRequirement(name = "JWT Security")

public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * Get all jobs --> svi objavljeni poslovi
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<JobDTO>>getJobs() {
        return ResponseEntity.ok(jobService.getJobs());
    }

  //Get all submitted applications - u application
    /**
     * Get a job by ID
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    /**
     * Get a job by position
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{position}")
    public ResponseEntity<JobDTO> getJobByPosition(@PathVariable String position) {
        return ResponseEntity.ok(jobService.getJobById(position));
    }


    /** Add a job*/
    @RequestMapping(method = RequestMethod.POST,path = "/addJob")
    //@PreAuthorize("hasAuthority('COMPANY_OWNER')")
    public ResponseEntity<JobDTO> addJob(@RequestBody JobRequestDTO job){
        return ResponseEntity.ok(jobService.addJob(job));
    }


    /**
     * Update a job by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<JobDTO> updateJob(@PathVariable String id, @RequestBody JobRequestDTO job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    /**
     * Delete a job
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteJob(@PathVariable("id") String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }


}

