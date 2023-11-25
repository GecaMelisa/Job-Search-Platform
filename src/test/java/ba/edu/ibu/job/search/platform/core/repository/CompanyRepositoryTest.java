package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Company;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void shouldReturnAllCompanies() {
        List<Company> companies = companyRepository.findAllCustom();

        assertEquals(2, companies.size());
    }

    @Test
    void shouldReturnAllCompaniesApprovedByAdmin() {
        List<Company> approvedCompanies = companyRepository.findByApprovedByAdmin(true);

        assertEquals(1, approvedCompanies.size());
    }

}
