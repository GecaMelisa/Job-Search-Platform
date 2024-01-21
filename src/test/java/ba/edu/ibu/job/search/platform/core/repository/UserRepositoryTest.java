package ba.edu.ibu.job.search.platform.core.repository;
import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnAllUsers() {

        List<User> users = userRepository.findAll();

        Assertions.assertFalse(users.isEmpty());

        Assertions.assertEquals(UserType.GUEST, users.get(0).getUserType());
    }



}
