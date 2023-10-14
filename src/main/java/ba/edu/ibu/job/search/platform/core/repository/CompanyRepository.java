package ba.edu.ibu.job.search.platform.core.repository;
import ba.edu.ibu.job.search.platform.core.model.Company;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies;

    public CompanyRepository() {
        this.companies = Arrays.asList(
                new Company(1, "Company 1", "Address 1", 062),
                new Company(2, "Company 2", "Address 2", 061),
                new Company(3, "Company 3", "Address 3", 060)
        );
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(int id) {
        return companies.stream().filter(company -> company.getId() == id).findFirst().orElse(null);
    }
}
