package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.ApplicationResponse;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Document
public class Application {

    @Id
    private String id;
    private Job job;
    private User user;
    private String userId;
    private String jobId;
    private String education;
    private String workExperience;
    private String coverLetter;
    private String applicationDate;
    private ApplicationResponse response;
    private String cv;

    public Application(User user, Job job, String coverLetter, String education, String workExperience, String applicationDate ) {
        this.user = user;
        this.job = job;
        this.workExperience=workExperience;
        this.education=education;
        this.coverLetter = coverLetter;
        this.applicationDate = applicationDate;
        this.response = null;
    }
    public Application(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }


    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate= applicationDate;
    }
    public void setCompanyOwner(CompanyOwner newCompanyOwner) {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}