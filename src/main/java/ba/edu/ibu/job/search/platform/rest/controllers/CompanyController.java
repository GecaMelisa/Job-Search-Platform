package ba.edu.ibu.job.search.platform.rest.controllers;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.service.CompanyService;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserDTO;
import ba.edu.ibu.job.search.platform.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@SecurityRequirement(name = "JWT Security")
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
     * Add a company - only companyOwner
     */
    @RequestMapping(method = RequestMethod.POST, path = "/register")
   // @PreAuthorize("hasAnyAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<CompanyDTO> register(@RequestBody CompanyRequestDTO company) {
        return ResponseEntity.ok(companyService.addCompany(company));
    }

    /** Get all unapporved companies - only admin */

    @RequestMapping(method = RequestMethod.GET, path = "/unapprovedCompanies")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CompanyDTO>> getUnapprovedCompanies() {
        List<CompanyDTO> unapprovedCompanies = companyService.getUnapprovedCompanies();
        return ResponseEntity.ok(unapprovedCompanies);
    }


    /**
     * Approve a company by ADMIN
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/approve/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> approveCompany(@PathVariable String companyId) {
        companyService.approveCompany(companyId);
        return ResponseEntity.ok("Company approved successfully");
    }

    /** Get all approved companies - only admin */

    @RequestMapping(method = RequestMethod.GET, path = "/approvedCompanies")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CompanyDTO>> getApprovedCompanies() {
        List<CompanyDTO> approvedCompanies = companyService.getApprovedCompanies();
        return ResponseEntity.ok(approvedCompanies);
    }


    /**
     * Update a company by ID
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAnyAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable String id, @RequestBody CompanyRequestDTO company) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }

    /**
     * Delete a company by ID
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAnyAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") String companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok("Company deleted successfully");
    }


    /**
     * Filter companies by email
     */
    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<CompanyDTO> filterCompany(@RequestParam String email) {
        return ResponseEntity.ok(companyService.filterByEmail(email));
    }
}

