package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.ApplicationResponse;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class SubmitAppDTO {

    private String id;
    private UserDTO user;
    private JobDTO job;
    private String education;
    private String workExperience;
    private String cv;
    private String applicationDate;
    private ApplicationResponse response;


    public SubmitAppDTO(Application application) {
        this.id = application.getId();
        this.user = new UserDTO(application.getUser());
        this.job = new JobDTO(application.getJob());
        this.education=application.getEducation();
        this.cv = application.getCv();
        this.applicationDate = application.getApplicationDate();
        this.response = application.getResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public JobDTO getJob() {
        return job;
    }

    public void setJob(JobDTO job) {
        this.job = job;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public ApplicationResponse getResponse() {
        return response;
    }

    public void setResponse(ApplicationResponse response) {
        this.response = response;
    }
}




