package ba.edu.ibu.job.search.platform.core.repository;

import ba.edu.ibu.job.search.platform.core.model.CompanyOwner;
import com.mongodb.client.MongoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyOwnerRepository extends MongoRepository <CompanyOwner, String> {

}
