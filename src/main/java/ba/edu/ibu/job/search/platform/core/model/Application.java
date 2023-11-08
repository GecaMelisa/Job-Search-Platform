package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Application {

    @Id
    private String id;
    private Job job; //za koji posao //kad pravim firmu trebadmin da to approve-a

   // da bi owner mogao vidjeti app - novi repo i novi servis  comp owner id -
    private User user; //koji user je aplicirao
    private String cv;
    private String contactEmail;
    private StatusRequest statusRequest; // enum
    private String applicationDate;
    public List<Application> getSubmittedApplications;

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

    public List<Application> getGetSubmittedApplications() {
        return getSubmittedApplications;
    }

    public void setGetSubmittedApplications(List<Application> getSubmittedApplications) {
        this.getSubmittedApplications = getSubmittedApplications;
    }
}