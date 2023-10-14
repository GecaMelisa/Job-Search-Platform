package ba.edu.ibu.job.search.platform.core.service;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

     //Dependency injection.
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    public Company findById(@PathVariable int id) {
        return companyRepository.findById(id);
    }
}