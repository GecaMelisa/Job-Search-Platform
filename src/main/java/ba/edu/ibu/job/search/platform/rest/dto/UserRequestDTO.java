package ba.edu.ibu.job.search.platform.rest.dto;

import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.springframework.stereotype.Component;
import java.util.Date;

public class UserRequestDTO {
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    /**Converting from a model to a DTO

    Empty constructor*/
    public  UserRequestDTO() { }

    /**Constructor which takes in the Model object as parameter*/
    public UserRequestDTO(User user) {
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    /**Converting from a DTO to a model with method toEntity which creates an empty Model object and assigns the data from the DTO to the model*/
    public User toEntity() {
        User user = new User();
        user.setUserType(userType);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreationDate(new Date());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}