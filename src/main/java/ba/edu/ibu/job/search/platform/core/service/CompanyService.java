package ba.edu.ibu.job.search.platform.core.service;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.repository.CompanyOwnerRepository;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyOwnerRepository companyOwnerRepository;
    private CompanyOwnerService companyOwnerService;


    /**
     * Dependency injection.
     */
    public CompanyService(CompanyRepository companyRepository, CompanyOwnerRepository companyOwnerRepository, CompanyOwnerService companyOwnerService) {
        this.companyRepository = companyRepository;
        this.companyOwnerRepository=companyOwnerRepository;
        this.companyOwnerService=companyOwnerService;
    }

    /**
     * Get all companies
     */
    public List<CompanyDTO> getCompanies() {
        List<Company> companies = companyRepository.findAll();

        return companies
                .stream()
                .map(CompanyDTO::new)
                .collect(toList());
    }

    /**
     * Get a company by id
     */
    public CompanyDTO getCompanyById(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ResourceNotFoundException("The company with the given ID does not exist.");
        }
        return new CompanyDTO(company.get());
    }
    /**
     * Get a company by id
     */
    public Company getCompanyById2(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ResourceNotFoundException("The company with the given ID does not exist.");
        }
        return company.get();
    }


    /**
     * Add a company - needs to be approved by ADMIN
     */
    public CompanyDTO addCompany(CompanyRequestDTO payload) {
        Company company = payload.toEntity();
        company.setApprovedByAdmin(false); // dok admin ne odobri

        // Dohvati vlasnika kompanije iz baze koristeći ID
        Optional<CompanyOwner> optionalCompanyOwner = companyOwnerRepository.findById(payload.getCompanyOwnerId());
        if (optionalCompanyOwner.isPresent()) {
            CompanyOwner companyOwner = optionalCompanyOwner.get();
            company.setCompanyOwner(companyOwner); // postaviti vlasnika kompanije
        } else {
            throw new ResourceNotFoundException("The company owner with the given ID does not exist.");
        }

        company = companyRepository.save(company);
        return new CompanyDTO(company);
    }


        /*Get all unapproved companies*/
    public List<CompanyDTO> getUnapprovedCompanies() {
        List<Company> unapprovedCompanies = companyRepository.findByApprovedByAdmin(false);
        return unapprovedCompanies
                .stream()
                .map(CompanyDTO::new)
                .collect(toList());
    }


    /* Approve Company */
    public void approveCompany(String companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setApprovedByAdmin(true); //odobrio
            companyRepository.save(company);
        } else {
            throw new ResourceNotFoundException("Company with id " + companyId + " not found");
        }
    }

    /*Get all approved companies*/
    public List<CompanyDTO> getApprovedCompanies() {
        List<Company> unapprovedCompanies = companyRepository.findByApprovedByAdmin(true);
        return unapprovedCompanies
                .stream()
                .map(CompanyDTO::new)
                .collect(toList());
    }


    /**
     * Update a company by id
     */
    public CompanyDTO updateCompany(String id, CompanyRequestDTO payload) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ResourceNotFoundException("The company with the given ID does not exist.");
        }
        Company updatedCompany = payload.toEntity();
        updatedCompany.setId(company.get().getId());
        updatedCompany = companyRepository.save(updatedCompany);
        return new CompanyDTO(updatedCompany);
    }

    /**Delete a company*/
    public void deleteCompany(String id) {
        Optional<Company> company = companyRepository.findById(id);
        company.ifPresent(companyRepository::delete);
    }

    /**Filter companies by email*/
    public CompanyDTO filterByEmail(String email) {
        Optional<Company> company = companyRepository.findFirstByEmailLike(email);
        return company.map(CompanyDTO::new).orElse(null);
    }









}