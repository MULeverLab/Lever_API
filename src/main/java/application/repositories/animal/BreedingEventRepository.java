package application.repositories.animal;

import application.entities.animal.Animal;
import application.entities.animal.BreedingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface BreedingEventRepository extends JpaRepository<BreedingEvent, Integer> {

    Optional<List<BreedingEvent>> findByDadBreeder(Animal dadBreeder);

    Optional<List<BreedingEvent>> findByMomBreeder(Animal momBreeder);

    Optional<List<BreedingEvent>> findByPairFormingDateGreaterThan(Long pairFormingDate);

    Optional<List<BreedingEvent>> findByPairFormingDateLessThan(Long pairFormingDate);

    Optional<List<BreedingEvent>> findByWeanedDayGreaterThan(Long weanedDate);

    Optional<List<BreedingEvent>> findByWeanedDayLessThan(Long weanedDate);

}
