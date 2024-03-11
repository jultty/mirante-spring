package mirante.api.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends JpaRepository<Account, String> {

  @Query(value = "SELECT a FROM Account a where a.registration = ?1 and a.password = ?2")
  Account login(String registration, String password);
  Account findByToken(String token);

}
