package application.repositories.animal;

import application.entities.animal.Animal;
import application.entities.animal.Genotype;
import application.entities.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<List<Animal>> findAnimalsByIdGreaterThan(Integer id);

    Animal findAnimalById(Integer id);

    Optional<List<Animal>> findAnimalsByGenotype(Genotype genotype);

    Optional<Animal> findAnimalByPhenotypeBridgeSetContaining(PhenotypeBridge phenotypeBridge);

    Optional<List<Animal>> findAnimalsBySex(Integer sex);

    Optional<List<Animal>> findAnimalsByDateOfBirthGreaterThan(Long dateOfBirth);

    Optional<List<Animal>> findAnimalsByDateOfDeathGreaterThan(Long dateOfDeath);

    Optional<List<Animal>> findAnimalsByDateOfBirthLessThan(Long dateOfBirth);

    Optional<List<Animal>> findAnimalsByDateOfDeathLessThan(Long dateOfDeath);

    Optional<List<Animal>> findAnimalsByCauseOfDeath(Integer causeOfDeath);

    Optional<List<Animal>> findAnimalsBySpecies(Integer species);

}
