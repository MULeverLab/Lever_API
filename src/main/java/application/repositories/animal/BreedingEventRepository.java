package application.repositories.animal;

import application.entities.animal.Animal;
import application.entities.animal.BreedingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface BreedingEventRepository extends JpaRepository<BreedingEvent, Integer> {

    BreedingEvent findBreedingEventById(Integer id);

    Optional<List<BreedingEvent>> findBreedingEventByIdGreaterThan(Integer id);

    Optional<List<BreedingEvent>> findBreedingEventsByDadBreeder(Animal dadBreeder);

    Optional<List<BreedingEvent>> findBreedingEventsByMomBreeder(Animal momBreeder);

    Optional<List<BreedingEvent>> findBreedingEventsByPairFormingDateGreaterThan(Long pairFormingDate);

    Optional<List<BreedingEvent>> findBreedingEventsByPairFormingDateLessThan(Long pairFormingDate);

    Optional<List<BreedingEvent>> findBreedingEventsByWeanedDayGreaterThan(Long weanedDate);

    Optional<List<BreedingEvent>> findBreedingEventsByWeanedDayLessThan(Long weanedDate);



}
