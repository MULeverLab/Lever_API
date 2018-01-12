package application.repository;

import application.animal.Colony;
import application.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ColonyRepository extends JpaRepository<Colony, Integer> {

    Colony findColonyById(Integer id);

    Optional<Set<Colony>> findColoniesByIdGreaterThan(Integer id);
}
