package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {

    @MockBean
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    @Test
    public void shouldReturnCompanyWhenCreated() {

        Company company = new Company();
        CompanyOwner companyOwner = new CompanyOwner();
        List<Job> jobs = Arrays.asList(new Job());
        List<User> employees = Arrays.asList(new User());

        company.setId("companyId");
        company.setCompanyOwner(companyOwner);
        company.setCompanyName("Test Name");
        company.setAddress("Test Address");
        company.setPhone("Test Phone");
        company.setEmail("Test Email");
        company.setJobs(jobs);
        company.setEmployees(employees);
        company.setStatusRequest(StatusRequest.APPROVED);

        Mockito.when(companyRepository.save(ArgumentMatchers.any(Company.class))).thenReturn(company);

        // Act
        CompanyDTO savedCompanyDTO = companyService.addCompany(new CompanyRequestDTO());

        assertThat(savedCompanyDTO.getCompanyName()).isEqualTo(company.getCompanyName());
        assertThat(savedCompanyDTO.getPhone()).isEqualTo(company.getPhone());
        assertThat(savedCompanyDTO.getId()).isEqualTo(company.getId());
    }
}
