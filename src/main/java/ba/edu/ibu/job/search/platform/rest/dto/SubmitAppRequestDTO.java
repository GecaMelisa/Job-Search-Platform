package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;

public class SubmitAppRequestDTO {

    private String userId;
    private String jobId;
    //private String firstName;
    //private String lastName;
   // private String email;
    //private String phoneNumber;
    //private String address;
    private String education;
    private String workExperience;
    private String cv;


    /**
     * Converting from a model to a DTO
     * Empty constructor
     */
    public SubmitAppRequestDTO() {
    }

    /**
     * Constructor which takes in the Model object as parameter
     */
    public SubmitAppRequestDTO(Application application, String userId, String jobId, String companyOwnerId) {
        this.userId=userId;
        this.jobId=jobId;
        //this.firstName=getFirstName();
        //this.lastName = getLastName();
        //this.email = getEmail();
        //this.phoneNumber = getPhoneNumber();
        //this.address = getAddress();
        this.education = getEducation();
        this.workExperience = getWorkExperience();
        this.cv = application.getCv();

    }

    /**
     * Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model
     */
    public Application toEntity() {
        Application application = new Application();
        application.setUserId(this.userId);
        application.setJobId(this.jobId);
   /*     this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setEducation(education);
        this.setWorkExperience(workExperience); */
        application.setCv(cv);

        return application;
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

    /*
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
*/
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }


}


