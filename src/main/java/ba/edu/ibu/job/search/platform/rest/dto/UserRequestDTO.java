package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.springframework.stereotype.Component;
import java.util.Date;

public class UserRequestDTO {
    private UserType userType;
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
    private String creationDate;

    /**Converting from a model to a DTO

    Empty constructor*/
    public  UserRequestDTO() { }

    /**Constructor which takes in the Model object as parameter*/
    public UserRequestDTO(User user) {
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth=user.getDateOfBirth();
        this.email = user.getEmail();
        this.address=user.getAddress();
        this.phoneNumber=user.getPhoneNumber();
        this.education=user.getEducation();
        this.workExperience=user.getWorkExperience();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.creationDate=user.getCreationDate();
    }

    /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
    public User toEntity() {
        User user = new User();
        user.setUserType(userType);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setEducation(education);
        user.setWorkExperience(workExperience);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreationDate(creationDate);

        return user;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}