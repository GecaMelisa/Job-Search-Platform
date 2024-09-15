package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.JobSearchPlatformApplication;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = JobSearchPlatformApplication.class)
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private Company testCompany1;
    private Company testCompany2;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test
        companyRepository.deleteAll();

        CompanyOwner owner1 = new CompanyOwner();
        owner1.setId("owner1");

        CompanyOwner owner2 = new CompanyOwner();
        owner2.setId("owner2");

        testCompany1 = new Company(
                "1",
                owner1,
                "Company One",
                "Address One",
                "123456789",
                "email1@example.com",
                Arrays.asList(new Job()), // Assuming Job constructor or mocks
                StatusRequest.APPROVED,
                "Description One"
        );

        testCompany2 = new Company(
                "2",
                owner2,
                "Company Two",
                "Address Two",
                "987654321",
                "email2@example.com",
                Arrays.asList(new Job()), // Assuming Job constructor or mocks
                StatusRequest.REJECTED,
                "Description Two"
        );

        // Save test companies in repository
        companyRepository.save(testCompany1);
        companyRepository.save(testCompany2);
    }

    @Test
    public void shouldReturnAllCompanies() {
        List<Company> companies = companyRepository.findAllCustom();
        assertEquals(2, companies.size());
    }

    @Test
    public void shouldReturnCompaniesWithPaginationAndSearch() {
        List<Company> companies = companyRepository.findAllWithPaginationAndSearch(0, 1, "Address");
        assertEquals(1, companies.size());
        assertEquals("Company One", companies.get(0).getCompanyName());
    }

    @Test
    public void shouldCountSearchedCompanies() {
        Long count = companyRepository.countSearchedCompanies("Address");
        assertEquals(2, count);
    }
}
