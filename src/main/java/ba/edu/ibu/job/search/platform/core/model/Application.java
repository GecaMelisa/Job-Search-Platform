package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Application {

    @Id
    private String id;

    //Provjeriti da li povezujemo preko modela ili preko id
    private Job job;
    private User user;
    private String userId;
    private String jobId;
    private String job_title;
    private String cv;
    private String contactEmail;
    private StatusRequest statusRequest; // enum
    private String applicationDate;
    public List<Application> submittedApplications;

    public Application(User user, Job job, String cv, String contactEmail, StatusRequest statusRequest, String applicationDate, List<Application> getSubmitetedApplications, String job_title) {
        this.user = user;
        this.job = job;
        this.job_title=job_title;
        this.cv = cv;
        this.contactEmail = contactEmail;
        this.statusRequest = statusRequest;
        this.applicationDate = applicationDate;
        this.submittedApplications = submittedApplications;
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


    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public StatusRequest getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(StatusRequest statusRequest) {
        this.statusRequest = statusRequest;
    }
    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate= applicationDate;
    }
    public void setCompanyOwner(CompanyOwner newCompanyOwner) {
    }

    public List<Application> getSubmittedApplications() {
        return submittedApplications;
    }

    public void setSubmittedApplications(List<Application> submittedApplications) {
        this.submittedApplications = submittedApplications;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
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
}