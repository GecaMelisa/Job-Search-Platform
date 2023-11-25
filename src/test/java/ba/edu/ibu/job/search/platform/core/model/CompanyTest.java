package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyTest {

    @Test
    void shouldCreateNewCompany() {
        // Kreiranje objekta Company
        CompanyOwner companyOwner = new CompanyOwner();
        List<Job> jobs = Arrays.asList(new Job());
        List<User> employees = Arrays.asList(new User());

        Company company = new Company();
        company.setId("companyId");
        company.setCompanyOwner(companyOwner);
        company.setCompanyName("Test Name");
        company.setAddress("Test Address");
        company.setPhone("Test Phone");
        company.setEmail("Test Email");
        company.setJobs(jobs);
        company.setEmployees(employees);
        company.setApprovedByAdmin(true);
        company.setStatusRequest(StatusRequest.APPROVED);

        assertEquals("companyId", company.getId());
        assertEquals(companyOwner, company.getCompanyOwner());
        assertEquals("Test Name", company.getCompanyName());
        assertEquals("Test Address", company.getAddress());
        assertEquals("Test Phone", company.getPhone());
        assertEquals("Test Email", company.getEmail());
        assertEquals(jobs, company.getJobs());
        assertEquals(employees, company.getEmployees());
        assertEquals(true, company.isApprovedByAdmin());
        assertEquals(StatusRequest.APPROVED, company.getStatusRequest());
    }
}
