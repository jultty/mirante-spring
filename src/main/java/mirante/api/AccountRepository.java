package mirante.api;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends 
  PagingAndSortingRepository<Account, String>,
  CrudRepository<Account, String> {
    List<Account> findByUsername(@Param("username") String username);
}
