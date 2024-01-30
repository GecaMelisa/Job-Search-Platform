package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import ba.edu.ibu.job.search.platform.rest.dto.JobDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.List;

@Document
public class CompanyOwner extends User {

        private List<JobDTO> jobs;

        private List<SubmitAppDTO> submittedApplications;

        public CompanyOwner(){};

        public CompanyOwner(String id, UserType userType, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, String address, String education, String workExperience, String username, String password, String creationDate, List<Application> applications) {
            super(id, userType, firstName, lastName, dateOfBirth, email, phoneNumber, address, education, workExperience, username, password, creationDate, applications);
        }

    public List<JobDTO> getJobs() {
            return jobs;
        }

        public void setJobs(List<JobDTO> jobs) {
            this.jobs = jobs;
        }

        public List<SubmitAppDTO> getSubmittedApplications() {
            return submittedApplications;
        }

        public void setSubmittedApplications(List<SubmitAppDTO> submittedApplications) {
            this.submittedApplications = submittedApplications;
        }


}


