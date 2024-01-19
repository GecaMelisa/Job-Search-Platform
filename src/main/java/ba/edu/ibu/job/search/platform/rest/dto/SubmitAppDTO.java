package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class SubmitAppDTO {

        private UserDTO user;
        private JobDTO job;
        private String cv;
        private String applicationDate;
        private StatusRequest statusRequest;
        private List<Application> submittedApplications;


    public SubmitAppDTO(Application application) {
        this.user = new UserDTO(application.getUser());
        this.job = new JobDTO(application.getJob());
        this.cv = application.getCv();
        this.applicationDate = application.getApplicationDate();
        this.statusRequest = application.getStatusRequest();
        this.submittedApplications = application.getSubmittedApplications() != null ? new ArrayList<>(application.getSubmittedApplications()) : new ArrayList<>();
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

        public StatusRequest getStatusRequest() {
            return statusRequest;
        }

        public void setStatusRequest(StatusRequest statusRequest) {
            this.statusRequest = statusRequest;
        }

        public List<Application> getSubmittedApplications() {
            return submittedApplications;
        }

        public void setSubmittedApplications(List<Application> submittedApplications) {
            this.submittedApplications = submittedApplications;
        }
}


