package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobRequestDTO {
    @Id
    private String id;
    private Company companyId;
    private String position;
    private String description;
    private String location;
    private JobType jobType;
    private int salary;
    private List<Job> requirements;
    private String postedDate;
    private String deadline;


        /**Converting from a model to a DTO

        Empty constructor*/
        public JobRequestDTO() {
        }

        /**Constructor which takes in the Model object as parameter*/

        public JobRequestDTO(Job job) {
            this.id = job.getId();
            this.companyId=job.getCompanyId();
            this.position = job.getPosition();
            this.description = job.getDescription();
            this.location = job.getLocation();
            this.jobType = job.getJobType();
            this.salary=job.getSalary();
            this.requirements = new ArrayList<>(job.getRequirements());
            this.postedDate=job.getPostedDate();
            this.deadline=job.getDeadline();
        }

        /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
        public Job toEntity() {
            Job job = new Job();
            job.setCompanyId(companyId);
            job.setPosition(position);
            job.setDescription(description);
            job.setLocation(location);
            job.setSalary(salary);
            job.setJobType(jobType);
            job.setRequirements(requirements);
            job.setPostedDate(postedDate);
            job.setDeadline(deadline);

            return job;
        }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Company getCompanyId() {
                return companyId;
            }

            public void setCompanyId(Company companyId) {
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

        public List<Job> getRequirements() {
            return requirements;
        }

        public void setRequirements(List<Job> requirements) {
            this.requirements = requirements;
        }
}



