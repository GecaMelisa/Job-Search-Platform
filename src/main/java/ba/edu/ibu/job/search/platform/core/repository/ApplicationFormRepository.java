package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.ApplicationForm;
import ba.edu.ibu.job.search.platform.core.model.Company;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationFormRepository extends MongoRepository<ApplicationForm, String> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<ApplicationForm> findAllCustom();
    @Query(value="{email:'?0'}", fields="{'id': 1, 'firstName': 1, 'lastName': 1,'address': 1, 'phoneNumber': 1, 'email': 1, 'dateOfBirth':1, 'education':1, workExperience':1, 'cv':1, 'applicationDate':1")
    Optional<ApplicationForm> findByEmailCustom(String email);

    Optional<ApplicationForm> findFirstByEmailLike(String emailPattern);

}

