package ba.edu.ibu.job.search.platform.rest.controllers;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.service.CompanyService;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Get all companies
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }

    /**
     * Get a company by ID
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    /**
     * Add a company
     */
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<CompanyDTO> register(@RequestBody CompanyRequestDTO company) {
        return ResponseEntity.ok(companyService.addCompany(company));
    }

    /**
     * Update a company by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable String id, @RequestBody CompanyRequestDTO company) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }

    /**
     * Delete a company
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Filter companies by email
     */
    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<CompanyDTO> filterCompany(@RequestParam String email) {
        return ResponseEntity.ok(companyService.filterByEmail(email));
    }
}

