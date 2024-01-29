package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.JobSearchPlatformApplication;
import ba.edu.ibu.job.search.platform.core.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {JobSearchPlatformApplication.class, CompanyRepository.class})
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void shouldReturnAllCompanies() {
        List<Company> companies = companyRepository.findAllCustom();

        assertEquals(2, companies.size());
    }



}
