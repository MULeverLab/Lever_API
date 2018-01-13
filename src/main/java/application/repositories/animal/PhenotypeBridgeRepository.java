package application.repositories.animal;

import application.entities.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhenotypeBridgeRepository extends JpaRepository<PhenotypeBridge, Integer> {

    Optional <List<PhenotypeBridge>> findPheontypeBridgeByPhenotype(Integer phenotype);

}
