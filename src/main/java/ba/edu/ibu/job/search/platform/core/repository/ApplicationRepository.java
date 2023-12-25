package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Application;
import ba.edu.ibu.job.search.platform.core.model.Company;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<Application> findAllCustom();

    Optional<Application> findApplicationsByUserId(String userId);

    List<Application> findApplicationsByJobId(String testJobId);
}

