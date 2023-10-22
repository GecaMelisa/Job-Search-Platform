package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.ApplicationForm;
import ba.edu.ibu.job.search.platform.core.repository.ApplicationFormRepository;
import ba.edu.ibu.job.search.platform.rest.dto.ApplicationFormDTO;
import ba.edu.ibu.job.search.platform.rest.dto.ApplicationFormRequestDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
@Service
public class ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;

    /**
     * Dependency injection.
     */
    public ApplicationFormService(ApplicationFormRepository applicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
    }

    /**
     * Get all applications
     */
    public List<ApplicationFormDTO> getApplications() {
        List<ApplicationForm> applications = applicationFormRepository.findAll();

        return applications
                .stream()
                .map(ApplicationFormDTO::new)
                .collect(toList());
    }

    /**
     * Get an application form by id
     */
    public ApplicationFormDTO getApplicationById(String id) {
        Optional<ApplicationForm> applicationForm = applicationFormRepository.findById(id);
        if (applicationForm.isEmpty()) {
            throw new ResourceNotFoundException("The application with the given ID does not exist.");
        }
        return new ApplicationFormDTO(applicationForm.get());
    }

    /**
     * Add an application form
     */
    public ApplicationFormDTO addApplication(ApplicationFormRequestDTO payload) {
        ApplicationForm applicationForm = applicationFormRepository.save(payload.toEntity());
        return new ApplicationFormDTO(applicationForm);
    }

    /**
     * Update an application form by id
     */
    public ApplicationFormDTO updateApplication(String id, ApplicationFormRequestDTO payload) {
        Optional<ApplicationForm> applicationForm = applicationFormRepository.findById(id);
        if (applicationForm.isEmpty()) {
            throw new ResourceNotFoundException("The application form with the given ID does not exist.");
        }
        ApplicationForm updatedApplication = payload.toEntity();
        updatedApplication.setId(applicationForm.get().getId());
        updatedApplication = applicationFormRepository.save(updatedApplication);
        return new ApplicationFormDTO(updatedApplication);
    }

    /**
     * Delete an application form
     */
    public void deleteApplication(String id) {
        Optional<ApplicationForm> applicationForm = applicationFormRepository.findById(id);
        applicationForm.ifPresent(applicationFormRepository::delete);
    }

    /**
     * Filter applications by email
     */
    public ApplicationFormDTO filterByEmail(String email) {
        Optional<ApplicationForm> applicationForm = applicationFormRepository.findFirstByEmailLike(email);
        return applicationForm.map(ApplicationFormDTO::new).orElse(null);
    }
}

