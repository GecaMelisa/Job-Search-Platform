package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.service.ApplicationFormService;
import ba.edu.ibu.job.search.platform.rest.dto.ApplicationFormDTO;
import ba.edu.ibu.job.search.platform.rest.dto.ApplicationFormRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/applications")

public class ApplicationFormController {

        private final ApplicationFormService applicationFormService;

        public ApplicationFormController(ApplicationFormService applicationFormService) {
            this.applicationFormService = applicationFormService;
        }

        /**
         * Get all applications
         */
        @RequestMapping(method = RequestMethod.GET, path = "/")
        public ResponseEntity<List<ApplicationFormDTO>> getApplications() {
            return ResponseEntity.ok(applicationFormService.getApplications());
        }

        /**
         * Get an application by ID
         */
        @RequestMapping(method = RequestMethod.GET, path = "/{id}")
        public ResponseEntity<ApplicationFormDTO> getApplicationById(@PathVariable String id) {
            return ResponseEntity.ok(applicationFormService.getApplicationById(id));
        }

        /**
         * Add an application form
         */
        @RequestMapping(method = RequestMethod.POST, path = "/register")
        public ResponseEntity<ApplicationFormDTO> register(@RequestBody ApplicationFormRequestDTO applicationForm) {
            return ResponseEntity.ok(applicationFormService.addApplication(applicationForm));
        }

        /**
         * Update an application by ID
         */
        @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
        public ResponseEntity<ApplicationFormDTO> updateApplication(@PathVariable String id, @RequestBody ApplicationFormRequestDTO applicationForm) {
            return ResponseEntity.ok(applicationFormService.updateApplication(id, applicationForm));
        }

        /**
         * Delete an application
         */
        @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
        public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
            applicationFormService.deleteApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        /**
         * Filter applications by email
         */
        @RequestMapping(method = RequestMethod.GET, path = "/filter")
        public ResponseEntity<ApplicationFormDTO> filterApplications(@RequestParam String email) {
            return ResponseEntity.ok(applicationFormService.filterByEmail(email));
        }
    }

