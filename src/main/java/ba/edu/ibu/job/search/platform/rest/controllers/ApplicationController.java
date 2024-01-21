package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.service.JobService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import ba.edu.ibu.job.search.platform.core.service.ApplicationService;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import ba.edu.ibu.job.search.platform.core.exceptions.auth.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/applications")
@SecurityRequirement(name = "JWT Security")

public class ApplicationController {

    @Autowired
    private  UserService userService;
    private  JobService jobService;
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService, UserService userService, JobService jobService) {
        this.applicationService = applicationService;
        this.userService=userService;
        this.jobService=jobService;
    }

    /**
     * Get all Applications
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<SubmitAppDTO>> getApplications() {
        return ResponseEntity.ok(applicationService.getApplications());
    }

    /**
     * Get an application by ID
     */
    @RequestMapping(method = RequestMethod.GET, path = "/byId/{id}")
    @PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<SubmitAppDTO> getApplicationById(@PathVariable String id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/byUserId/{userId}")
    @PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN', 'MEMBER')")
    public ResponseEntity<SubmitAppDTO> getApplicationByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(applicationService.getApplicationByUserId(userId));
    }

    /** Submit Application */

    @RequestMapping(method = RequestMethod.POST, path = "/submitApp")
    public ResponseEntity<SubmitAppDTO> submitApplication(@RequestBody SubmitAppRequestDTO application) {
        return ResponseEntity.ok(applicationService.addAppToJob(application));
    }


    /**
     * Delete an application
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

