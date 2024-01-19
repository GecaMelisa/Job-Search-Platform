package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.JobStatus;
import ba.edu.ibu.job.search.platform.rest.dto.SubmitAppDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;


import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document
public class Job {

    @Id
    private String id;
    private Company company;
    private String companyId;
    private String position;
    private String description;
    private String location;
    private JobType jobType;
    private int salary;
    private List <String> requirements;
    private String postedDate;
    private String deadline;
    private JobStatus jobStatus; //da znamo je li oglas jos uvijek aktivan ili nije
    private List<SubmitAppDTO> submittedApplications;

    public Job() {}

    // Konstruktor
    public Job(String id, Company company, String position, String description, String location,
               JobType jobType, int salary, List<String> requirements, String postedDate,
               String deadline, JobStatus jobStatus) {
        this.id = id;
        this.company = company;
        this.companyId = company.getId();
        this.position = position;
        this.description = description;
        this.location = location;
        this.jobType = jobType;
        this.salary = salary;
        this.requirements = requirements;
        this.postedDate = postedDate;
        this.deadline = deadline;
        this.jobStatus = jobStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String title) {
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

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
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

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public List<SubmitAppDTO> getSubmittedApplications() {
        return submittedApplications;
    }

    public void setSubmittedApplications(List<SubmitAppDTO> submittedApplications) {
        this.submittedApplications = submittedApplications;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}

