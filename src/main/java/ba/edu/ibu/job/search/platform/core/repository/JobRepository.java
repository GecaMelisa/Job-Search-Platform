package ba.edu.ibu.job.search.platform.core.repository;
import ba.edu.ibu.job.search.platform.core.model.Company;
import ba.edu.ibu.job.search.platform.core.model.Job;
import ba.edu.ibu.job.search.platform.core.model.enums.JobType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<Job> findAllCustom();

    @Query(value="{position:'?0'}", fields="{'id': 1,  'company': 1, 'description': 1, 'location': 1, 'type':1, 'salary':1, 'requirements':1, 'contactEmail':1, 'postedDate':1, 'deadline':1")
    Optional<Job> findByPosition(String title);

    List <Job> findByCompanyId(String companyId);
    List<Job> findByJobType(JobType jobType);


    @Aggregation(pipeline = {
            "{ $match: { $or: [ { location: { $regex: '?2', $options: 'i' } }, { position: { $regex: '?2', $options: 'i' } } ] } }",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List<Job> findAllJobsWithPaginationAndSearch(Integer offset, Integer limit, String search);


    @Aggregation(pipeline = {
            "{ $match: { jobType: { $regex: '?2', $options: 'i' } },  }",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List <Job> findAllJobsWithPaginationAndFiltering(Integer offset, Integer limit, JobType jobType);

    @Query(value = "{ $or: [ { location: { $regex: '?0', $options: 'i' } }, { position: { $regex: '?0', $options: 'i' } } ] }", count = true)
    Long countSearchedJobs(String search);

    @Query(value = "{ jobType: { $regex: '?0', $options: 'i' }, }", count = true)
    Long countFilteredJobs(JobType jobType);

    @Aggregation(pipeline = {
            "{ $match: { $or: [ { location: { $regex: '?2', $options: 'i' } }, { position: { $regex: '?2', $options: 'i' } } ] },  { jobType: { $regex: '?3', $options: 'i' } }  }",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List<Job> findAllJobsWithPaginationAndSearchAndFiltering(Integer offset, Integer limit, String search, JobType jobType);

    @Query(value = "{ $or: [ { location: { $regex: '?0', $options: 'i' } }, { position: { $regex: '?0', $options: 'i' } } ] }, { jobType: { $regex: '?3', $options: 'i' } }  ", count = true)
    Long countSearchedAndFilteredJobs(String search, JobType jobType);

    List<String> jobTypes = Stream.of(JobType.values()).map(
            JobType::name).collect( Collectors.toList());





}

