package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.data.annotation.Id;

public class SubmitAppDTO {
        @Id
        private String id;
        private String userId;
        private String jobId;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private int phoneNumber;
        private String email;
        private String address;
        private String education;
        private String workExperience;
        private String username;
        private String password;
        private String cv;
        private String applicationDate;


        public SubmitAppDTO(Application application, User user) {
            this.id=application.getId();
            this.userId=application.getUserId();
            this.jobId=application.getJobId();
            this.firstName=user.getFirstName();
            this.lastName=user.getLastName();
            this.dateOfBirth=user.getDateOfBirth();
            this.phoneNumber=user.getPhoneNumber();
            this.email=user.getEmail();
            this.address=user.getAddress();
            this.education=user.getEducation();
            this.workExperience=user.getWorkExperience();
            this.username=user.getUsername();
            this.password=user.getPassword();
            this.applicationDate=application.getApplicationDate();

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
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

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public int getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getWorkExperience() {
            return workExperience;
        }

        public void setWorkExperience(String workExperience) {
            this.workExperience = workExperience;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCv() {
            return cv;
        }

        public void setCv(String cv) {
            this.cv = cv;
        }

        public String getApplicationDate() {
            return applicationDate;
        }

        public void setApplicationDate(String applicationDate) {
            this.applicationDate = applicationDate;
        }
}


