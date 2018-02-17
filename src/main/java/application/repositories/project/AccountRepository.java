package application.repositories.project;

import application.entities.project.UserCompetencyBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.project.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

    Optional<List<Account>> findByUsernameLike(String username);

    Optional<List<Account>> findByFirstNameLike(String firstName);

    Optional<List<Account>> findByLastNameLike(String lastname);

    Optional<List<Account>> findByEmailLike(String email);

    Optional<List<Account>> findByPhoneNumber(String phone);

    Optional<List<Account>> findByPrivilege(String privilege);

    Optional<List<Account>> findByUserCompetencyBridgeSetContains(UserCompetencyBridge userCompetencyBridge);
}
