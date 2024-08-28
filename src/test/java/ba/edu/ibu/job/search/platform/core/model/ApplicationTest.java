package ba.edu.ibu.job.search.platform.core.model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ApplicationTest {

    @Test
    void shouldCreateNewApplication() {
        User user = new User();
        Job job = new Job();

        Application application = new Application(
                user,
                job,
                "sample_cv.pdf",
                "Bachelor's Degree",
                "5 years",
                "2024-08-26"
        );

        Assertions.assertEquals("sample_cv.pdf", application.getCv());
        Assertions.assertEquals("Bachelor's Degree", application.getEducation());
        Assertions.assertEquals("5 years", application.getWorkExperience());
        Assertions.assertEquals("2024-08-26", application.getApplicationDate());
        Assertions.assertEquals(user, application.getUser());
        Assertions.assertEquals(job, application.getJob());
    }

    @Test
    void shouldCompareTwoApplications() {
        User user = new User(); // Pretpostavljamo da je User klasa već definirana
        Job job = new Job();    // Pretpostavljamo da je Job klasa već definirana

        Application application1 = new Application(
                user,
                job,
                "sample_cv.pdf",
                "Bachelor's Degree",
                "5 years",
                "2024-08-26"
        );

        Application application2 = new Application(
                user,
                job,
                "sample_cv.pdf",
                "Bachelor's Degree",
                "5 years",
                "2024-08-26"
        );

        AssertionsForInterfaceTypes
                .assertThat(application1)
                .usingRecursiveComparison()
                .isEqualTo(application2);
    }
}
