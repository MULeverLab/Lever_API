package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.project.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
}
