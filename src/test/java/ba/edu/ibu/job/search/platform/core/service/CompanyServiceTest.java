package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.core.repository.CompanyOwnerRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private CompanyOwnerRepository companyOwnerRepository;

    @Autowired
    private CompanyService companyService;

    private CompanyOwner companyOwner;

    @BeforeEach
    public void setUp() {
        companyOwner = new CompanyOwner(
                "ownerId",
                UserType.COMPANY_OWNER,
                "John",
                "Doe",
                "1980-01-01",
                "john.doe@example.com",
                "123456789",
                "123 Street",
                "Bachelor's",
                "5 years experience",
                "john_doe",
                "password",
                "2024-01-01",
                Collections.emptyList(),
                null        );
    }

    @Test
    public void shouldReturnCompanyWhenCreated() {
        Company company = new Company();
        company.setId("companyId");
        company.setCompanyOwner(companyOwner);
        company.setCompanyName("Test Name");
        company.setAddress("Test Address");
        company.setPhone("Test Phone");
        company.setEmail("Test Email");
        company.setJobs(Arrays.asList(new Job()));
        company.setEmployees(Collections.emptyList());
        company.setStatusRequest(StatusRequest.APPROVED);

        Mockito.when(companyRepository.save(ArgumentMatchers.any(Company.class))).thenReturn(company);
        Mockito.when(companyOwnerRepository.findById("ownerId")).thenReturn(Optional.of(companyOwner));

        CompanyRequestDTO requestDTO = new CompanyRequestDTO();
        requestDTO.setCompanyOwnerId("ownerId");
        CompanyDTO savedCompanyDTO = companyService.addCompany(requestDTO);

        assertThat(savedCompanyDTO.getCompanyName()).isEqualTo(company.getCompanyName());
        assertThat(savedCompanyDTO.getPhone()).isEqualTo(company.getPhone());
        assertThat(savedCompanyDTO.getId()).isEqualTo(company.getId());
    }

    @Test
    public void shouldThrowExceptionWhenCompanyOwnerNotFound() {
        Mockito.when(companyOwnerRepository.findById("ownerId")).thenReturn(Optional.empty());

        CompanyRequestDTO requestDTO = new CompanyRequestDTO();
        requestDTO.setCompanyOwnerId("ownerId");

        Assertions.assertThatThrownBy(() -> companyService.addCompany(requestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The company owner with the given ID does not exist.");
    }

    @Test
    public void shouldReturnCompanyById() {
        Company company = new Company();
        company.setId("companyId");
        company.setCompanyName("Test Company");

        Mockito.when(companyRepository.findById("companyId")).thenReturn(Optional.of(company));

        CompanyDTO companyDTO = companyService.getCompanyById("companyId");

        assertThat(companyDTO.getCompanyName()).isEqualTo("Test Company");
    }

    @Test
    public void shouldThrowExceptionWhenCompanyNotFoundById() {
        Mockito.when(companyRepository.findById("companyId")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> companyService.getCompanyById("companyId"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The company with the given ID does not exist.");
    }

    @Test
    public void shouldReturnCompanyWhenFilteredByEmail() {
        Company company = new Company();
        company.setId("companyId");
        company.setEmail("test@example.com");

        Mockito.when(companyRepository.findFirstByEmailLike("test@example.com")).thenReturn(Optional.of(company));

        CompanyDTO companyDTO = companyService.filterByEmail("test@example.com");

        assertThat(companyDTO.getEmail()).isEqualTo("test@example.com");
    }
}
