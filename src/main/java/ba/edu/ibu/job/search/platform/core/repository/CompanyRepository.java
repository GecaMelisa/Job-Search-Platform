package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.Company;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<Company> findAllCustom();

    @Query(value="{email:'?0'}", fields="{'id': 1, 'companyName': 1, 'address': 1, 'phone': 1, 'email': 1}")
    Optional<Company> findByEmailCustom(String email);

    Optional<Company> findFirstByEmailLike(String emailPattern);

    @Aggregation(pipeline = {
            "{ $match: { $or: [ { email: { $regex: '?2', $options: 'i' } }, { address: { $regex: '?2', $options: 'i' } } ] } }",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List<Company> findAllWithPaginationAndSearch(Integer offset, Integer limit, String search);

    @Query(value = "{ $or: [ { email: { $regex: '?0', $options: 'i' } }, { address: { $regex: '?0', $options: 'i' } } ] }", count = true)
    Long countSearchedCompanies(String search);
}
