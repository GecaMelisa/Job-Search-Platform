package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.*;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.core.repository.*;

import ba.edu.ibu.job.search.platform.rest.dto.*;
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
    private UserRepository userRepository;
    private JobRepository jobRepository;

    private UserService userService;
    private JobService jobService;


    /**
     * Dependency injection.
     */

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              CompanyOwnerRepository companyOwnerRepository,
                              JobRepository jobRepository,
                              UserService userService,
                              CompanyOwnerService companyOwnerService,
                              JobService jobService) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.userService = userService;
        this.jobService = jobService;
    }


    /**
     * Get all SubmittedApplications
     */
    public List<SubmitAppDTO> getApplications() {
        List<Application> applications = applicationRepository.findAll();

        return applications
                .stream()
                .map(application -> {
                    User user = userRepository.findById(application.getUser().getId()).orElse(null);
                    return new SubmitAppDTO(application);
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
        User user = userRepository.findById(application.getId()).orElse(null);

        return new SubmitAppDTO(application);
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

        return new SubmitAppDTO(application);
    }


    public void submitApplicationToJob(String applicationId, String jobId){
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()){
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }
        SubmitAppDTO application = getApplicationById(applicationId);
        List<SubmitAppDTO> applications = job.get().getSubmittedApplications();
        applications.add(application);
        job.get().setSubmittedApplications(applications);
        //ovdje je sejvano treba samo memerbdto popravit
        jobRepository.save(job.get());
        // return new TrainerDTO(trainer.get());
    }

    public SubmitAppDTO submitApplication(SubmitAppRequestDTO payload) {

        String jobId=payload.getJobId();
        Application application = payload.toEntity();
        String applicationId= payload.getJobId();

        //member.setUserType(UserType.MEMBER);

        if (jobId != null) {

            Job newJob=jobService.getJobById2(jobId);
            application.setJob(newJob);

            //memberRepository.save(member);
        }

        applicationRepository.save(application);

        //userRepository.save(application);

        Application application2=applicationRepository.save(application);
        submitApplicationToJob(application2.getId(),jobId );

        return new SubmitAppDTO(application);

    }



    /**
     * Submit an application to job


    public void submitApplicationToJob(String applicationId, String jobId){
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()){
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }

        SubmitAppDTO application = getApplicationById(applicationId);
        List<SubmitAppDTO> applications = job.get().getSubmittedApplications();
        applications.add(application);
        job.get().setSubmittedApplications(applications);
    }




    public SubmitAppDTO submitApplication(SubmitAppRequestDTO payload)

        String jobId = payload.getJobId();
        Application application = payload.toEntity();

        if (jobId != null) {
            Optional<Job> optionalJob = jobRepository.findById(jobId);
            if (optionalJob.isEmpty()) {
                throw new ResourceNotFoundException("The job with the given ID does not exist.");
            }
            Job job = optionalJob.get();
            application.setJob(job);
        }

        applicationRepository.save(application);

        return new SubmitAppDTO(application);


        Application application = payload.toEntity(); //konvertuje podatke iz SubmitAppRequestDTO u objekat application

        String userId = payload.getUserId(); //dohvaćajmo userID
        User user = userService.getUserById2(userId); //uzimamo userId ali  ne ide preko DTO
        application.setUser(user);


        String jobId = payload.getJobId();
        Job newJob = jobService.getJobById2(jobId);
        application.setJob(newJob);


        application = applicationRepository.save(application);
        return new SubmitAppDTO(application);
    }
*/

    /**
     * Update an application form by id

    public PostApplicationDTO updateApplication(String id, PostApplicationRequestDTO payload) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application form with the given ID does not exist.");
        }
        Application existingApplication = applicationOptional.get();

        // Dohvati Job i Company objekte iz repozitorija
        Job job = jobRepository.findById(existingApplication.getJobId()).orElseThrow(() -> new EntityNotFoundException("Application not found"));

        Application updatedApplication = payload.toEntity();
        updatedApplication.setId(existingApplication.getId());
        updatedApplication = applicationRepository.save(updatedApplication);

        return new PostApplicationDTO(updatedApplication);
    }
*/
    /**
     * Delete an application form
     */
    public void deleteApplication(String id) {
        Optional<Application> application = applicationRepository.findById(id);
        application.ifPresent(applicationRepository::delete);
    }

    }


