package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = Arrays.asList(
                new User(1, "Melisa", "Geca", "melisa.geca@stu.ibu.edu.ba"),
                new User(2, "Aldin", "Kovačević", "aldin.kovacevic@ibu.edu.ba")
        );
    }

    public List<User> findAll() {
        return users;
    }
}