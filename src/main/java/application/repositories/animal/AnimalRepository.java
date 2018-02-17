package application.repositories.animal;

import application.entities.animal.Animal;
import application.entities.animal.Genotype;
import application.entities.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<List<Animal>> findByGenotype(Genotype genotype);

    Optional<Animal> findByPhenotypeBridgeSetContaining(PhenotypeBridge phenotypeBridge);

    Optional<List<Animal>> findBySex(Integer sex);

    Optional<List<Animal>> findByDateOfBirthGreaterThan(Long dateOfBirth);

    Optional<List<Animal>> findByDateOfDeathGreaterThan(Long dateOfDeath);

    Optional<List<Animal>> findByDateOfBirthLessThan(Long dateOfBirth);

    Optional<List<Animal>> findByDateOfDeathLessThan(Long dateOfDeath);

    Optional<List<Animal>> findByCauseOfDeath(Integer causeOfDeath);

    Optional<List<Animal>> findBySpecies(Integer species);

}
