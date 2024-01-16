package ba.edu.ibu.job.search.platform.core.repository;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<Job> findAllCustom();

    @Query(value="{position:'?0'}", fields="{'id': 1,  'company': 1, 'description': 1, 'location': 1, 'type':1, 'salary':1, 'requirements':1, 'contactEmail':1, 'postedDate':1, 'deadline':1")
    Optional<Job> findByPosition(String title);

    List <Job> findByCompanyId(String companyId);
}

