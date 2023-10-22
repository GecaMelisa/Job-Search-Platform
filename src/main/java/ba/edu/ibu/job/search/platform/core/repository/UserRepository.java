package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.User;
import ba.edu.ibu.job.search.platform.core.model.enums.UserType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //Aggregation - primjena mongoDB agregacioni pipline za dohvat podataka iz baze
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } } //match za filtriranje dokumenata
    """)
    List<User> findAllCustom();

    @Query(value="{email:'?0'}", fields="{'id': 1, 'firstName': 1, 'lastName': 1, 'email': 1, 'username': 1, 'userType': 1}") //1 - ukljuceno, 0 - ne ukljucuje
    Optional<User> findByEmailCustom(String email);

    Optional<User> findFirstByEmailLike(String emailPattern);

    List<User> findByEmailAndUserTypeOrderByCreationDateDesc(String email, UserType userType);


}