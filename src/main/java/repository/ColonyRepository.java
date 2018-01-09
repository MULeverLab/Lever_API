package repository;

import animal.Colony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonyRepository extends JpaRepository<Colony, Integer> {
}
