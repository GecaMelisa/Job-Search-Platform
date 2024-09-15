package ba.edu.ibu.job.search.platform.core.service;

import ba.edu.ibu.job.search.platform.core.api.file.FileService;
import ba.edu.ibu.job.search.platform.core.exceptions.GeneralException;
import ba.edu.ibu.job.search.platform.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.job.search.platform.core.model.*;
import ba.edu.ibu.job.search.platform.core.model.enums.ApplicationResponse;
import ba.edu.ibu.job.search.platform.core.repository.*;

import ba.edu.ibu.job.search.platform.rest.dto.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;
    private JobRepository jobRepository;

    private UserService userService;
    private JobService jobService;
    private EmailService emailService;

    private final FileService awsFileService;


    /**
     * Dependency injection.
     */

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              JobRepository jobRepository,
                              UserService userService,
                              JobService jobService,
                              EmailService emailService,
                              FileService awsFileService) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.userService = userService;
        this.jobService = jobService;
        this.emailService = emailService;
        this.awsFileService = awsFileService;
    }

    /**
     * Get all Applications
     */
    public List<SubmitAppDTO> getApplications() {
        List<Application> applications = applicationRepository.findAll();

        return applications
                .stream()
                .map(SubmitAppDTO::new)
                .peek(o -> o.setCv(awsFileService.getFileUrl(o.getCv())))
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
        Optional<Application> applicationOptional = applicationRepository.findById(userId);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application with the given UserID does not exist.");
        }
        Application application = applicationOptional.get();
        User user = userRepository.findById(userId).orElse(null);

        return new SubmitAppDTO(application);
    }

    /**
     * Get an application by userId2 - (ne)ide preko DTO
     */

    public Application getApplicationById2(String id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new ResourceNotFoundException("The app with the given ID does not exist.");
        }
        return application.get();
    }


    public SubmitAppDTO addAppToJob(SubmitAppRequestDTO application) {
        System.out.println(application.getJobId());
        System.out.println("melisa");
        // Provjeri postoji li posao s danim ID-om
        Optional<Job> optionalJob = jobRepository.findById(application.getJobId());
        if (optionalJob.isEmpty()) {
            throw new ResourceNotFoundException("The job with the given ID does not exist.");
        }

        // Pokušaj upload-ovati CV.
        FileUploadResponseDTO uploadedCv;
        try {
            uploadedCv = awsFileService.uploadFile(application.getCv());
        } catch (FileUploadException e) {
            throw new GeneralException("The CV file could not be uploaded. Please try again.");
        }

        // Dohvati posao
        Job job = optionalJob.get();

        Application applicationEntity = application.toEntity();

        // Connection with user and Job
        applicationEntity.setUser(userService.getCurrentUser());
        applicationEntity.setJob(job);
        applicationEntity.setCv(uploadedCv.getFilePath());
        applicationEntity.setEducation(application.getEducation());
        applicationEntity.setWorkExperience(application.getWorkExperience());

        applicationRepository.save(applicationEntity);

        return new SubmitAppDTO(applicationEntity);
    }

    /**
     * Delete an application form
     */
    public void deleteApplication(String id) {
        Optional<Application> application = applicationRepository.findById(id);
        application.ifPresent(applicationRepository::delete);
    }


    public List<SubmitAppDTO> getAppByUserId(String userId) {
        List<Application> applications = applicationRepository.findByUserId(userId);
        return applications
                .stream()
                .map(SubmitAppDTO::new)
                .collect(toList());
    }

    public Application updateResponse(String id, UpdateApplicationResponseDTO data) {
        Optional<Application> applicationObject = applicationRepository.findById(id);
        Application application = applicationObject.get();
        application.setResponse(data.getResponse());
        applicationRepository.save(application);
        String body = "";
        if(data.getResponse() == ApplicationResponse.ACCEPTED)
            body = "Your job application has been accepted. Congratulations!";
        else if(data.getResponse() == ApplicationResponse.DECLINED)
            body = "Your job application has been declined. We are sorry :(";
        emailService.sendEmail(data.getToEmail(), "Application response", body);
        return application;
    }

    /*
    public SubmitAppDTO getAppPoUserId(String userId) {
        List <Application> applicationOptional = applicationRepository.findByUserId(userId);
        if (applicationOptional.isEmpty()) {
            throw new ResourceNotFoundException("The application with the given ID does not exist.");
        }

        Application application = applicationOptional.get();
        User user = userRepository.findById(application.getId()).orElse(null);

        return new SubmitAppDTO(application);
    }*/



}

