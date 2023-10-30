package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.ApplicationRepository;

import ba.edu.ibu.job.search.platform.core.repository.CompanyRepository;
import ba.edu.ibu.job.search.platform.core.repository.JobRepository;
import ba.edu.ibu.job.search.platform.core.repository.UserRepository;
import ba.edu.ibu.job.search.platform.rest.dto.PostApplicationDTO;
import ba.edu.ibu.job.search.platform.rest.dto.PostApplicationRequestDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.edu.ibu.job.search.platform.core.exceptions.auth.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import ba.edu.ibu.job.search.platform.core.exceptions.auth.EntityNotFoundException;
import static java.util.stream.Collectors.toList;
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    private  JobRepository jobRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    /**
     * Dependency injection.
     */
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    /**
     * Get all applications
     */
    public List<SubmitAppDTO> getApplications() {
        List<Application> applications = applicationRepository.findAll();

        return applications
                .stream()
                .map(application -> {
                    User user = userRepository.findById(application.getUserId()).orElse(null);
                    return new SubmitAppDTO(application, user);
                })
                .collect(toList());
    }


    /**
     * Get an application by id
     */
    public SubmitAppDTO getApplicationById(String id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application with the given ID does not exist.");
        }

        Application application = applicationOptional.get();
        User user = userRepository.findById(application.getUserId()).orElse(null);

        return new SubmitAppDTO(application, user);
    }


    /**
     * Get an application by userId
     */
    public SubmitAppDTO getApplicationByUserId(String userId) {
        Optional<Application> applicationOptional = applicationRepository.findApplicationByUserId(userId);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application with the given User_ID does not exist.");
        }
        Application application = applicationOptional.get();
        User user = userRepository.findById(userId).orElse(null);

        return new SubmitAppDTO(application, user);
    }


    /** ?????????????????????????
     * Create an application
     * UserPrincipal - da bi dobili podatke o trenutnom useru, u ovom slucaju userType- spring security
     */
    public PostApplicationDTO postApplication(PostApplicationRequestDTO payload, User currentUser)  {
        if (currentUser.getUserType() != UserType.ADMIN) {
            throw new AccessDeniedException("Only admins can create applications.");
        }

        Application application = payload.toEntity();
        application = applicationRepository.save(application);

        // Dohvaća Job i Company objekte iz repozitorija
        Job job = jobRepository.findById(application.getJobId()).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Company company = companyRepository.findById(application.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));

        return new PostApplicationDTO(application, job, company);
    }


    /**
     * Submit an application
     */
    public SubmitAppDTO submitApplication(SubmitAppRequestDTO payload, User currentUser) {
        if (currentUser.getUserType() != UserType.MEMBER) {
            throw new AccessDeniedException("Only regitered users can submit applications.");
        }
        Application application= applicationRepository.save(payload.toEntity());

        User user = userRepository.findById(application.getUserId()).orElseThrow(()-> new EntityNotFoundException("User not found"));
        return new SubmitAppDTO(application, user);
    }


    /**
     * Update an application form by id
     */
    public PostApplicationDTO updateApplication(String id, PostApplicationRequestDTO payload) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application form with the given ID does not exist.");
        }
        Application existingApplication = applicationOptional.get();

        // Dohvati Job i Company objekte iz repozitorija
        Job job = jobRepository.findById(existingApplication.getJobId()).orElseThrow(() -> new EntityNotFoundException("Application not found"));
        Company company = companyRepository.findById(existingApplication.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Application updatedApplication = payload.toEntity();
        updatedApplication.setId(existingApplication.getId());
        updatedApplication = applicationRepository.save(updatedApplication);

        return new PostApplicationDTO(updatedApplication, job, company);
    }

    /**
     * Delete an application form
     */
    public void deleteApplication(String id) {
        Optional<Application> application = applicationRepository.findById(id);
        application.ifPresent(applicationRepository::delete);
    }


}

