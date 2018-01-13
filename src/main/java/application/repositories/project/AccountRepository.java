package application.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.project.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountById(Integer id);

    Optional<List<Account>> findAccountsByUsernameLike(String username);

    Optional<List<Account>> findAccountsByFirstNameLike(String firstName);

    Optional<List<Account>> findAccountsByLastNameLike(String lastname);

    Optional<List<Account>> findAccountsByEmailLike(String email);

    Optional<List<Account>> findAccountsByPhoneNumber(String phone);

    Optional<List<Account>> findAccountsByPrivilege(String privilege);

}
