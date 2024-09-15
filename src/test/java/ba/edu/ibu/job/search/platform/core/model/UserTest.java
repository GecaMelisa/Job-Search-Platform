package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ba.edu.ibu.job.search.platform.core.model.enums.UserType.ADMIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    void shouldSetAndGetAttributes() {
        User user = new User();
        user.setId("newId");
        user.setFirstName("newFirstName");
        user.setLastName("newLastName");
        user.setEmail("newEmail@gmail.com");
        user.setPhoneNumber("061-999-888");
        user.setAddress("NewAddress");
        user.setEducation("NewEducation");
        user.setWorkExperience("NewExperience");
        user.setUsername("newUsername");
        user.setPassword("newPassword");
        user.setCreationDate("01.01.2024");

        assertEquals("newId", user.getId());
        assertEquals("newFirstName", user.getFirstName());
        assertEquals("newLastName", user.getLastName());
        assertEquals("newEmail@gmail.com", user.getEmail());
        assertEquals("061-999-888", user.getPhoneNumber());
        assertEquals("NewAddress", user.getAddress());
        assertEquals("NewEducation", user.getEducation());
        assertEquals("NewExperience", user.getWorkExperience());
        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("01.01.2024", user.getCreationDate());
    }

    @Test
    void shouldReturnCorrectAuthorities() {
        User user = new User();
        user.setUserType(UserType.ADMIN);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ADMIN")));
    }

    @Test
    void shouldReturnEmptyAuthoritiesWhenUserTypeIsNull() {
        User user = new User();

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertTrue(authorities.isEmpty());
    }

    @Test
    void shouldAlwaysReturnTrueForAccountNonExpired() {
        User user = new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void shouldAlwaysReturnTrueForAccountNonLocked() {
        User user = new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void shouldAlwaysReturnTrueForCredentialsNonExpired() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void shouldAlwaysReturnTrueForIsEnabled() {
        User user = new User();
        assertTrue(user.isEnabled());
    }

}
