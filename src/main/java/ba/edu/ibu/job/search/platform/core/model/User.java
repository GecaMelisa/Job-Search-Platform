package ba.edu.ibu.job.search.platform.core.model;

import org.springframework.data.annotation.Id;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document
public class User {
    @Id
    private String id;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Date creationDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername(){
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

    public Date getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
}
