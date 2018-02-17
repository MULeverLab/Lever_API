package application.repositories.animal;

import application.entities.animal.Colony;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColonyRepository extends JpaRepository<Colony, Integer> {

}
