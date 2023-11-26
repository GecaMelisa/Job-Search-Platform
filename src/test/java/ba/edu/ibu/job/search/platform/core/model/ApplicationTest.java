package ba.edu.ibu.job.search.platform.core.model;

import ba.edu.ibu.job.search.platform.core.model.enums.StatusRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    User user = new User();
    Job job = new Job();

    @Test
    void shouldCreateNewApplication() {
        Application application = new Application(
                user,
                job,
                "applicantCV",
                "applicant@email.com",
                StatusRequest.PENDING,
                "applicationDate",
                null);


        Assertions.assertNull(application.getId());
        assertEquals(user, application.getUser());
        assertEquals(job, application.getJob());
        assertEquals("applicantCV", application.getCv());
        assertEquals("applicant@email.com", application.getContactEmail());
        assertEquals(StatusRequest.PENDING, application.getStatusRequest());
        assertEquals("applicationDate", application.getApplicationDate());
        Assertions.assertNull(application.getSubmittedApplications());
    }


        @Test
        void shouldReturnAllSubmittedApplications() {

            Application application = new Application(
                    user,
                    job,
                    "applicantCV",
                    "applicant@email.com",
                    StatusRequest.PENDING,
                    "applicationDate",
                    null);

            Application application2 = new Application(
                    user,
                    job,
                    "applicantCV2",
                    "applicant2@email.com",
                    StatusRequest.PENDING,
                    "applicationDate",
                    null);

            // Kreiranje liste SubmittedApplication
            List<Application> submittedApplications = new ArrayList<>();
            submittedApplications.add(new Application(user, job, "cv1", "email@email.com", StatusRequest.PENDING, "date1", null));
            submittedApplications.add(new Application(user, job, "cv2", "email2@email.com", StatusRequest.PENDING, "date2", null));

            application.setSubmittedApplications(submittedApplications);

            List<Application> returnedApplications = application.getSubmittedApplications();

            assertEquals(submittedApplications, returnedApplications);
        }
    }


