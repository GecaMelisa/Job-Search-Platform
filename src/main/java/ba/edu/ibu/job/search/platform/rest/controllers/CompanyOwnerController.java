package ba.edu.ibu.job.search.platform.rest.controllers;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.service.CompanyOwnerService;
import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyOwners")
public class CompanyOwnerController {

    private final CompanyOwnerService companyOwnerService;

    public CompanyOwnerController(CompanyOwnerService companyOwnerService) {
        this.companyOwnerService = companyOwnerService;
    }
    /**Get all company owners */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<CompanyOwnerDTO>> getCompanyOwners() {
        return ResponseEntity.ok(companyOwnerService.getCompanyOwners());
    }
    /**Get acompany owner by id */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CompanyOwnerDTO> getCompanyOwnerById(@PathVariable String id) {
        return ResponseEntity.ok(companyOwnerService.getCompanyOwnerById(id));
    }


    @RequestMapping(method = RequestMethod.POST,path = "/register")
    public ResponseEntity<CompanyOwnerDTO> createCompanyOwner(@RequestBody CompanyOwnerRequestDTO companyOwner){
        return ResponseEntity.ok(companyOwnerService.addCompanyOwner(companyOwner));
    }
    @RequestMapping(method = RequestMethod.PUT,path = "/{id}")
    public ResponseEntity<CompanyOwnerDTO> updateCompanyOwner(@PathVariable String id,@RequestBody CompanyOwnerRequestDTO companyOwner){
        return ResponseEntity.ok(companyOwnerService.updateCompanyOwner(id,companyOwner));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    public ResponseEntity<Void> deleteCompanyOwner(@PathVariable String id){
        companyOwnerService.deleteCompanyOwner(id);
        return null;
    }



}
