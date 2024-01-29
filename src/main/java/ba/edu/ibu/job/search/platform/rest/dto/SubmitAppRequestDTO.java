package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.User;

public class SubmitAppRequestDTO {

    private String jobId;
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
    public SubmitAppRequestDTO(Application application, String jobId, String companyOwnerId) {
        this.jobId=jobId;
        this.education = getEducation();
        this.workExperience = getWorkExperience();
        this.cv = application.getCv();

    }

    /**
     * Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model
     */
    public Application toEntity() {
        Application application = new Application();
        application.setJobId(this.jobId);
        application.setCv(cv);

        return application;
    }


    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }


}


