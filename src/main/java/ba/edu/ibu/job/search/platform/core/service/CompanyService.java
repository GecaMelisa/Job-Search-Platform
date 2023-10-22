package ba.edu.ibu.job.search.platform.core.service;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    /**
     * Dependency injection.
     */
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
     * Add a company
     */
    public CompanyDTO addCompany(CompanyRequestDTO payload) {
        Company company = companyRepository.save(payload.toEntity());
        return new CompanyDTO(company);
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