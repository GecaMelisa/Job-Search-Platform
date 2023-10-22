package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Job;

import java.util.Date;

public class JobRequestDTO {
        private String id;
        private String title;
        private String company;
        private String description;
        private String location;
        private String type;
        private String requirements;
        private String contactEmail;
        private Date postedDate;
        private Date deadline;


        /**Converting from a model to a DTO

        Empty constructor*/
        public JobRequestDTO() {
        }

        /**Constructor which takes in the Model object as parameter*/

        public JobRequestDTO(Job job) {
            this.title = job.getTitle();
            this.company=job.getCompany();
            this.description = job.getDescription();
            this.location = job.getLocation();
            this.type = job.getType();
            this.requirements=job.getRequirements();
            this.contactEmail=job.getContactEmail();
            this.postedDate=job.getPostedDate();
            this.deadline=job.getDeadline();
        }

        /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
        public Job toEntity() {
            Job job = new Job();
            job.setTitle(title);
            job.setCompany(company);
            job.setDescription(description);
            job.setLocation(location);
            job.setType(type);
            job.setRequirements(requirements);
            job.setContactEmail(contactEmail);
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRequirements() {
                return requirements;
        }

            public void setRequirements(String requirements) {
                this.requirements = requirements;
            }

            public String getContactEmail() {
                return contactEmail;
            }

            public void setContactEmail(String contactEmail) {
                this.contactEmail = contactEmail;
            }

            public Date getPostedDate() {
                return postedDate;
            }

            public void setPostedDate(Date postedDate) {
                this.postedDate = postedDate;
            }

            public Date getDeadline() {
                return deadline;
            }

            public void setDeadline(Date deadline) {
                this.deadline = deadline;
            }
}



