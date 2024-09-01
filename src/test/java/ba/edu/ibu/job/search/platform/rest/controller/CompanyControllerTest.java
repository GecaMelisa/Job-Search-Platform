package ba.edu.ibu.job.search.platform.rest.controller;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import ba.edu.ibu.job.search.platform.core.service.CompanyOwnerService;
import ba.edu.ibu.job.search.platform.core.service.CompanyService;
import ba.edu.ibu.job.search.platform.core.service.JwtService;
import ba.edu.ibu.job.search.platform.core.service.UserService;
import ba.edu.ibu.job.search.platform.rest.configuration.SecurityConfiguration;
import ba.edu.ibu.job.search.platform.rest.controllers.CompanyController;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyPageDTO;
import ba.edu.ibu.job.search.platform.rest.dto.CompanyRequestDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(CompanyController.class)
@Import(SecurityConfiguration.class)
public class CompanyControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyService companyService;

    @MockBean
    JwtService jwtService;

    @MockBean
    UserService userService;

    @MockBean
    CompanyOwnerService companyOwnerService;

    @MockBean
    AuthenticationProvider authenticationProvider;

    @Test
    void shouldReturnAllCompanies() throws Exception {
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

        Mockito.when(companyService.getCompanies()).thenReturn(List.of(new CompanyDTO(company)));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/companies/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        Assertions.assertEquals("Test Name", JsonPath.read(response, "$.[0].companyName"));
    }

    @Test
    void shouldReturnCompanyById() throws Exception {
        Company company = new Company();
        company.setId("companyId");
        company.setCompanyName("Test Company");

        Mockito.when(companyService.getCompanyById("companyId")).thenReturn(new CompanyDTO(company));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/companies/{id}", "companyId")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals("Test Company", JsonPath.read(response, "$.companyName"));
    }

    @Test
    void shouldFilterCompanyByEmail() throws Exception {
        Company company = new Company();
        company.setEmail("test@example.com");
        company.setCompanyName("Filtered Company");

        Mockito.when(companyService.filterByEmail("test@example.com")).thenReturn(new CompanyDTO(company));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/companies/filter")
                        .param("email", "test@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals("Filtered Company", JsonPath.read(response, "$.companyName"));
    }

    @Test
    void shouldReturnSortedCompanies() throws Exception {
        Company company1 = new Company();
        company1.setCompanyName("Company A");

        Company company2 = new Company();
        company2.setCompanyName("Company B");

        Mockito.when(companyService.sortBasedUponSomeField("name")).thenReturn(Arrays.asList(company1, company2));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/companies/sort/{field}", "name")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals("Company A", JsonPath.read(response, "$[0].companyName"));
        Assertions.assertEquals("Company B", JsonPath.read(response, "$[1].companyName"));
    }

}
