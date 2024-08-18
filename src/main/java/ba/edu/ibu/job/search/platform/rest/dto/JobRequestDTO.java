package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;

import java.util.List;

public class JobRequestDTO {
    private String companyId;
    private String position;
    private String description;
    private String location;
    private JobType jobType;
    private int salary;
    private List<String> requirements;
    private String deadline;

    private String seniority;


        /**Converting from a model to a DTO

        Empty constructor*/
        public JobRequestDTO() {
        }

        /**Constructor which takes in the Model object as parameter*/

        public JobRequestDTO(Job job, String companyId) {
            this.companyId=getCompanyId();
            this.position = job.getPosition();
            this.description = job.getDescription();
            this.location = job.getLocation();
            this.jobType = job.getJobType();
            this.salary=job.getSalary();
            this.requirements = new ArrayList<>(getRequirements());
            this.deadline=job.getDeadline();
            this.seniority = job.getSeniority();
        }

        /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
        public Job toEntity() {
            Job job = new Job();
            this.setCompanyId(companyId);
            job.setPosition(position);
            job.setDescription(description);
            job.setLocation(location);
            job.setSalary(salary);
            job.setJobType(jobType);
            job.setRequirements(requirements);
            job.setDeadline(deadline);
            job.setSeniority(seniority);
            return job;
        }

            public String getCompanyId() {
                return companyId;
            }
            public void setCompanyId(String companyId) {
                this.companyId = companyId;
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

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }
}





