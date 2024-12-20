package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.Application;

import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
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

    /** Get all submited applications for job*/
    @GetMapping("/{jobId}/applications")
    public ResponseEntity<List<Application>> getAllApplicationsForJob(@PathVariable String jobId) {
        List<Application> applications = jobService.getAllApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }

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
     @RequestMapping(method = RequestMethod.POST,path = "/createJob")
     //@PreAuthorize("hasAuthority('COMPANY_OWNER')")
     public ResponseEntity<JobDTO> createJob(@RequestBody JobRequestDTO job){
     return ResponseEntity.ok(jobService.createJob(job));
     }

    /** Get Jobs by CompanyId */
    @GetMapping("/byCompany/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable String companyId) {
        List<Job> jobs = jobService.getJobsByCompany(companyId);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    /**PAGINATION*/

    @RequestMapping(method = RequestMethod.GET, path = "/jobsWithPagination")
    public JobPageDTO getJobsWithPagination(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field ){
        return jobService.getJobsWithPagination(offset, pageSize, field);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jobsWithPaginationAndFiltering/{jobType}")
    public JobPageDTO getJobsWithPaginationAndFiltering(@RequestParam int offset, @RequestParam int pageSize, @RequestParam JobType jobType ){
        return jobService.getJobsWithPaginationAndFilteringg(offset, pageSize, jobType);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jobsWithPaginationAndSearchAndFiltering/{jobType}")
    public JobPageDTO getJobsWithPaginationAndFiltering(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String search, @RequestParam JobType jobType ){
        return jobService.getJobsWithPaginationAndSearchAndFilteringg(offset, pageSize, search, jobType);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getTypes")
    public ResponseEntity<List<String>>getTypes() {
        return ResponseEntity.ok(jobService.getTypes());
    }


    /**FILTERING*/

    @RequestMapping(method = RequestMethod.GET, path = "/type/{jobType}")
    public List<JobDTO> getJobsByType(@PathVariable JobType jobType) {
        return jobService.getJobsByType(jobType);
    }

    /**
     * Update a job by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    //@PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<JobDTO> updateJob(@PathVariable String id, @RequestBody JobRequestDTO job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    /**
     * Delete a job
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    //@PreAuthorize("hasAnyAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<String> deleteJob(@PathVariable("id") String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }


}

