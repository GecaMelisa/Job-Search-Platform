package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static ba.edu.ibu.job.search.platform.core.model.enums.UserType.ADMIN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {
    List<Application> applications = new ArrayList<>();
    @Test
    void shouldCreateNewUser() {

        User user = new User(
                "someId",
                ADMIN,
                "someName",
                "someLastName",
                "11.12.1899",
                "some@gmail.com",
                "061-444-555",
                "Sarajevo",
                "IBU",
                "No",
                "someUserName",
                "pass123",
                "26.11.2023",
                applications


        );

        // Assertions.assertEquals za svaki atribut
        assertEquals("someId", user.getId());
        assertEquals(UserType.ADMIN, user.getUserType());
        assertEquals("someName", user.getFirstName());
        assertEquals("someLastName", user.getLastName());
        assertEquals("11.12.1899", user.getDateOfBirth());
        assertEquals("some@gmail.com", user.getEmail());
        assertEquals("061-444-555", user.getPhoneNumber());
        assertEquals("Sarajevo", user.getAddress());
        assertEquals("IBU", user.getEducation());
        assertEquals("No", user.getWorkExperience());
        assertEquals("someUserName", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals("26.11.2023", user.getCreationDate());
        assertEquals(applications, user.getApplications());


    }

}
