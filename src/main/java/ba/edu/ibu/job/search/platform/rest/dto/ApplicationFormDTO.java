package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.ApplicationForm;

public class ApplicationFormDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;


    public ApplicationFormDTO(ApplicationForm applicationForm){
        this.id = applicationForm.getId();
        this.firstName=applicationForm.getFirstName();
        this.lastName=applicationForm.getLastName();
        this.email = applicationForm.getEmail();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
