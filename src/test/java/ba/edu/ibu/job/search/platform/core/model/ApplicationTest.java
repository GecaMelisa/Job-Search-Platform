package ba.edu.ibu.job.search.platform.core.model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ApplicationTest {
    @Test
    void shouldCompareTwoApplications() {
        User user = new User();
        Job job = new Job();

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
