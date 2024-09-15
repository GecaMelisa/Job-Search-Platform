package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.service.CompanyOwnerService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("api/companyOwners")
@SecurityRequirement(name = "JWT Security")

public class CompanyOwnerController {

    private final CompanyOwnerService companyOwnerService;

    public CompanyOwnerController(CompanyOwnerService companyOwnerService) {
        this.companyOwnerService = companyOwnerService;
    }
    /**Get all company owners */
    @RequestMapping(method = RequestMethod.GET, path = "/")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CompanyOwnerDTO>> getCompanyOwners() {
        return ResponseEntity.ok(companyOwnerService.getCompanyOwners());
    }

    /**Get acompany owner by id */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CompanyOwnerDTO> getCompanyOwnerById(@PathVariable String id) {
        return ResponseEntity.ok(companyOwnerService.getCompanyOwnerById(id));
    }

    /**Create a Company Owner*/

    @RequestMapping(method = RequestMethod.POST,path = "/register")
    public ResponseEntity<CompanyOwnerDTO> createCompanyOwner(@RequestBody CompanyOwnerRequestDTO companyOwner){
        return ResponseEntity.ok(companyOwnerService.addCompanyOwner(companyOwner));
    }

    /**Edit a Company Owner by ID*/
    @RequestMapping(method = RequestMethod.PUT,path = "/{id}")
    @PreAuthorize("hasAuthority('COMPANY_OWNER', 'ADMIN')")
    public ResponseEntity<CompanyOwnerDTO> updateCompanyOwner(@PathVariable String id,@RequestBody CompanyOwnerRequestDTO companyOwner){
        return ResponseEntity.ok(companyOwnerService.updateCompanyOwner(id,companyOwner));
    }


    /**Delete a Company Owner*/

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCompanyOwner(@PathVariable String id){
        companyOwnerService.deleteCompanyOwner(id);
        return null;
    }



}
