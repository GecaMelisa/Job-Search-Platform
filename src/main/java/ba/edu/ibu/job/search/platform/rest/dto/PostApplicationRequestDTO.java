package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.springframework.data.annotation.Id;

import java.util.List;


public class PostApplicationRequestDTO {
    @Id
    private String id;
    private String jobId;
    private String companyName;
    private String position;
    private String description;
    private String location;
    private JobType jobType;
    private List<Job> requirements;
    private int salary;
    private String cv;
    private String contactEmail;
    private String postedDate;
    private String deadline;
    private StatusRequest statusRequest;


    /**Converting from a model to a DTO

         Empty constructor*/
        public PostApplicationRequestDTO() { //ZA ADMINA KOJI CREATE-A APP
        }

        /**Constructor which takes in the Model object as parameter*/
        public PostApplicationRequestDTO(Application application, Job job, Company company) {
            this.id=application.getId();
            this.jobId=application.getJobId();
            this.companyName=company.getCompanyName();
            this.position=job.getPosition();
            this.description=job.getDescription();
            this.location=job.getLocation();
            this.jobType=job.getJobType();
            this.requirements=job.getRequirements();
            this.salary = job.getSalary();
            this.cv=application.getCv();
            this.statusRequest=application.getStatusRequest();
            this.contactEmail=application.getContactEmail();
            this.postedDate=job.getPostedDate();
            this.deadline=job.getDeadline();

        }

        /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
        public Application toEntity() {
            Application application = new Application();
            Job job = new Job();
            Company company = new Company();
            company.setCompanyName(companyName);
            job.setPosition(position);
            job.setDescription(description);
            job.setLocation(location);
            job.setJobType(jobType);
            job.setRequirements(requirements);
            job.setSalary(salary);
            application.setCv(cv);
            application.setStatusRequest(statusRequest);
            application.setContactEmail(contactEmail);
            job.setPostedDate(postedDate);
            job.setDeadline(deadline);

            return application;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            id = id;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

         public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public JobType getJobType() {
            return jobType;
        }

        public void setJobType(JobType jobType) {
            this.jobType = jobType;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
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

        public String getPostedDate() {
            return postedDate;
        }

        public void setPostedDate(String postedDate) {
            this.postedDate = postedDate;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public List<Job> getRequirements() {
            return requirements;
        }

        public void setRequirements(List<Job> requirements) {
            this.requirements = requirements;
        }

        public StatusRequest getStatusRequest() {
            return statusRequest;
        }

        public void setStatusRequest(StatusRequest statusRequest) {
            this.statusRequest = statusRequest;
        }
}
