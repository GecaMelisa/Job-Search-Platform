package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;

import java.util.List;

public class CompanyOwnerDTO extends UserDTO {
    private String userId;

    private List<JobDTO> jobs;

    public List<JobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }


    public CompanyOwnerDTO(CompanyOwner companyOwner) {
        super(companyOwner);
        this.jobs = companyOwner.getJobs();
        this.userId = companyOwner.getUserId();
    }
}
