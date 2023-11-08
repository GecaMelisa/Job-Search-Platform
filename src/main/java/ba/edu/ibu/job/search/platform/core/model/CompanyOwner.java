package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.rest.dto.JobDTO;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class CompanyOwner extends User {
        private List<JobDTO> jobs;

        private List<SubmitAppDTO> submittedApplications;

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


