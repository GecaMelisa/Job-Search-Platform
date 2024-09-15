package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    private final CompanyOwner companyOwner = new CompanyOwner();
    private final List<Job> jobs = Arrays.asList(new Job());

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
                StatusRequest.APPROVED,
                "Test Description"
        );

        assertEquals("companyId", company.getId());
        assertEquals(companyOwner, company.getCompanyOwner());
        assertEquals("Test Name", company.getCompanyName());
        assertEquals("Test Address", company.getAddress());
        assertEquals("Test Phone", company.getPhone());
        assertEquals("Test Email", company.getEmail());
        assertEquals(jobs, company.getJobs());
        assertEquals(StatusRequest.APPROVED, company.getStatusRequest());
        assertEquals("Test Description", company.getDescription());
    }

    @Test
    void shouldUpdateCompanyDetails() {
        Company company = new Company();
        company.setId("newCompanyId");
        company.setCompanyOwner(companyOwner);
        company.setCompanyName("New Test Name");
        company.setAddress("New Test Address");
        company.setPhone("New Test Phone");
        company.setEmail("newTestEmail@test.com");
        company.setJobs(jobs);
        company.setStatusRequest(StatusRequest.PENDING);
        company.setDescription("New Test Description");

        assertEquals("newCompanyId", company.getId());
        assertEquals(companyOwner, company.getCompanyOwner());
        assertEquals("New Test Name", company.getCompanyName());
        assertEquals("New Test Address", company.getAddress());
        assertEquals("New Test Phone", company.getPhone());
        assertEquals("newTestEmail@test.com", company.getEmail());
        assertEquals(jobs, company.getJobs());
        assertEquals(StatusRequest.PENDING, company.getStatusRequest());
        assertEquals("New Test Description", company.getDescription());
    }

    @Test
    void shouldReturnCorrectCompanyOwnerId() {
        CompanyOwner owner = new CompanyOwner();
        owner.setId("ownerId");

        Company company = new Company(
                "companyId",
                owner,
                "Test Name",
                "Test Address",
                "Test Phone",
                "Test Email",
                jobs,
                StatusRequest.APPROVED,
                "Test Description"
        );

        assertEquals("ownerId", company.getCompanyOwner().getId());
    }

    @Test
    void shouldHaveNoEmployeesInitially() {
        Company company = new Company(
                "companyId",
                companyOwner,
                "Test Name",
                "Test Address",
                "Test Phone",
                "Test Email",
                jobs,
                StatusRequest.APPROVED,
                "Test Description"
        );

        assertNull(company.getEmployees());
    }

    @Test
    void shouldAddEmployeesToCompany() {
        User employee1 = new User();
        User employee2 = new User();
        List<User> employees = Arrays.asList(employee1, employee2);

        Company company = new Company();
        company.setEmployees(employees);

        assertNotNull(company.getEmployees());
        assertEquals(2, company.getEmployees().size());
        assertEquals(employee1, company.getEmployees().get(0));
        assertEquals(employee2, company.getEmployees().get(1));
    }

    @Test
    void shouldSetDescription() {
        Company company = new Company();
        String description = "A test company description.";
        company.setDescription(description);

        assertEquals(description, company.getDescription());
    }
}
