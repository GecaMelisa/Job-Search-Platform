package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyTest {

    CompanyOwner companyOwner = new CompanyOwner();
    List<Job> jobs = Arrays.asList(new Job());

    @Test
    void shouldCreateNewCompany() {

        Company company = new Company(
                "companyId",
                companyOwner,
                "Test Name",
                "Test Address",
                "Test Phone",
                "Test Email",
                jobs,
                true,
                StatusRequest.APPROVED
        );

        assertEquals("companyId", company.getId());
        assertEquals(companyOwner, company.getCompanyOwner());
        assertEquals("Test Name", company.getCompanyName());
        assertEquals("Test Address", company.getAddress());
        assertEquals("Test Phone", company.getPhone());
        assertEquals("Test Email", company.getEmail());
        assertEquals(jobs, company.getJobs());
        assertEquals(true, company.isApprovedByAdmin());
        assertEquals(StatusRequest.APPROVED, company.getStatusRequest());
    }
}
