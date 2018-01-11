package application.repository;

import application.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PhenotypeBridgeRepository extends JpaRepository<PhenotypeBridge, Integer> {

    Optional <Set<PhenotypeBridge>> findPheontypeBridgeByPhenotype(Integer phenotype);

}
