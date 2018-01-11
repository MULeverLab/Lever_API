package application.repository;

import application.animal.Animal;
import application.animal.Genotype;
import application.animal.PhenotypeBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Optional<Set<Animal>> findAnimalsById(Integer id);

    Optional<Set<Animal>> findAnimalsByGenotype(Genotype genotype);

    Optional<Set<Animal>> findAnimalsByPhenotypeBridgeSetContaining(PhenotypeBridge phenotypeBridge);

    Optional<Set<Animal>> findAnimalsBySex(Integer sex);

    Optional<Set<Animal>> findAnimalsByDateOfBirth(Long dateOfBirth);

    Optional<Set<Animal>> findAnimalsByDateOfDeath(Long dateOfDeath);

    Optional<Set<Animal>> findAnimalsByCauseOfDeath(Integer causeOfDeath);

    Optional<Set<Animal>> findAnimalsBySpecies(Integer species);
}
