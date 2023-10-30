package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.service.JobService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import ba.edu.ibu.job.search.platform.core.service.ApplicationService;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import ba.edu.ibu.job.search.platform.core.exceptions.auth.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/applications")

public class ApplicationController {

    @Autowired
    private  UserService userService;
    private  JobService jobService;
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Get all applications
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<SubmitAppDTO>> getApplications() {
        return ResponseEntity.ok(applicationService.getApplications());
    }

    /**
     * Get an application by ID
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<SubmitAppDTO> getApplicationById(@PathVariable String id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    /**
     * Get an application by userId
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public ResponseEntity<SubmitAppDTO> getApplicationByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(applicationService.getApplicationByUserId(userId));
    }

    /**
     * Create an application*/

    @RequestMapping(method = RequestMethod.POST, path = "/postApp")
    public ResponseEntity<PostApplicationDTO> register(@RequestBody PostApplicationRequestDTO application) {
        try {
            return ResponseEntity.ok(applicationService.postApplication(application, new User()));
        }
        catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }

    /** Submit Application*/

    @RequestMapping(method = RequestMethod.POST, path = "/submitApp")
    public ResponseEntity<SubmitAppDTO> submitApplication(@RequestBody SubmitAppRequestDTO application, User currentUser) {
        // Dohvat ID-a korisnika iz trenutnog korisnika
        String userId = currentUser.getId();

        // Povezivanje korisnika s aplikacijom
        application.setUserId(userId);

        // Spremanje aplikacije u bazu
        SubmitAppDTO savedApplication = applicationService.submitApplication(application, currentUser);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }



    /**
     * Update an application by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<PostApplicationDTO> updateApplication(@PathVariable String id, @RequestBody PostApplicationRequestDTO application) {
        return ResponseEntity.ok(applicationService.updateApplication(id, application));
    }

    /**
     * Delete an application
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

