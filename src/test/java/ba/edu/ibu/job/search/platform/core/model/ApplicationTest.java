package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    void shouldCreateNewApplication() {
        User user = new User();

        Job job = new Job();

        // Kreiranje objekta Application
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);  // Povezivanje prijave sa poslom
        application.setCv("applicantCV");
        application.setContactEmail("applicant@email.com");
        application.setStatusRequest(StatusRequest.PENDING);
        application.setApplicationDate("applicationDate");
        application.setGetSubmittedApplications(null);

        Assertions.assertNull(application.getId());  // Ako ne postavljamo id prilikom kreiranja
        Assertions.assertEquals(user, application.getUser());
        Assertions.assertEquals(job, application.getJob());
        Assertions.assertEquals("applicantCV", application.getCv());
        Assertions.assertEquals("applicant@email.com", application.getContactEmail());
        Assertions.assertEquals(StatusRequest.PENDING, application.getStatusRequest());
        Assertions.assertEquals("applicationDate", application.getApplicationDate());
        Assertions.assertNull(application.getGetSubmittedApplications());  // Ako ne postavljate submitted applications prilikom kreiranja
    }
}
