package ba.edu.ibu.job.search.platform.rest.dto;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {
    private String id;
    private String name;
    private UserType userType;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String education;
    private String workExperience;
    private String address;
    private String creationDate;
    private List<SubmitAppDTO> applications;


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.userType = user.getUserType();
        this.dateOfBirth = user.getDateOfBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.applications = new ArrayList<>();
    }

    public UserDTO() {
        this.applications = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

}
