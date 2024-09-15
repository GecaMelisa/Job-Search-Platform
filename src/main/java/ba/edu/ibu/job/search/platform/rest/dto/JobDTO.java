package ba.edu.ibu.job.search.platform.rest.dto;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;

import java.util.ArrayList;
import java.util.List;

public class JobDTO {

    private String jobId;
    private String companyName;
    private String position;
    private String description;
    private String location;
    private JobType jobType;
    private int salary;
    private List<String> requirements;
    private String postedDate;
    private String deadline;
    private StatusRequest statusRequest;
    private String seniority;
    private CompanyDTO company;
    private String companyId;

    public JobDTO(Job job){

       // this.company=new CompanyDTO(job.getCompany());
        if (job.getCompany() != null) {
            this.companyName = job.getCompany().getCompanyName();
            this.company = new CompanyDTO(job.getCompany());
            this.companyId = job.getCompany().getId();
        } else {
            this.companyName = "N/A";
        }
        this.jobId=job.getId();
        this.position = job.getPosition();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.jobType = job.getJobType();
        this.salary=job.getSalary();
//        this.requirements = new ArrayList<>(job.getRequirements());
        List<String> requirements = job.getRequirements();
        if (requirements != null) {
            this.requirements = new ArrayList<>(requirements);
        } else {
            this.requirements = new ArrayList<>(); // Initialize as an empty list
        }
        this.postedDate=job.getPostedDate();
        this.deadline=job.getDeadline();
        this.statusRequest=getStatusRequest();
        this.seniority=job.getSeniority();
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
    public StatusRequest getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(StatusRequest statusRequest) {
        this.statusRequest = statusRequest;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String  companyName) {
        this.companyName = companyName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}

