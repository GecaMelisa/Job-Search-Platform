package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.ApplicationForm;

import java.util.Date;

    public class ApplicationFormRequestDTO {
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private String email;
        private int phoneNumber;
        private String address;
        private String education;
        private String workExperience;
        private String cv;


        /**Converting from a model to a DTO

        Empty constructor*/
        public ApplicationFormRequestDTO() {
        }

        /**Constructor which takes in the Model object as parameter*/
        public ApplicationFormRequestDTO(ApplicationForm applicationForm) {
            this.firstName = applicationForm.getFirstName();
            this.lastName = applicationForm.getLastName();
            this.dateOfBirth = applicationForm.getDateOfBirth();
            this.email = applicationForm.getEmail();
            this.phoneNumber=applicationForm.getPhoneNumber();
            this.address=applicationForm.getAddress();
            this.education=applicationForm.getEducation();
            this.workExperience=applicationForm.getWorkExperience();
            this.cv=applicationForm.getCv();
        }

        /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
        public ApplicationForm toEntity() {
            ApplicationForm applicationForm = new ApplicationForm();
            applicationForm.setFirstName(firstName);
            applicationForm.setLastName(lastName);
            applicationForm.setDateOfBirth(dateOfBirth);
            applicationForm.setEmail(email);
            applicationForm.setPhoneNumber(phoneNumber);
            applicationForm.setAddress(address);
            applicationForm.setEducation(education);
            applicationForm.setWorkExperience(workExperience);
            applicationForm.setCv(cv);

            return applicationForm;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public int getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWorkExperience() {
            return workExperience;
        }

        public void setWorkExperience(String workExperience) {
            this.workExperience = workExperience;
        }

        public String getCv() {
            return cv;
        }

        public void setCv(String cv) {
            this.cv = cv;
        }
    }
