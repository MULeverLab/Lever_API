package application.repository;

import application.animal.Colony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonyRepository extends JpaRepository<Colony, Integer> {
}
