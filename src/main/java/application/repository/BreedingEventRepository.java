package application.repository;

import application.animal.Animal;
import application.animal.BreedingEvent;
import application.animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BreedingEventRepository extends JpaRepository<BreedingEvent, Integer> {

    BreedingEvent findBreedingEventById(Integer id);

    Optional<Set<BreedingEvent>> findBreedingEventByIdGreaterThan(Integer id);

    Optional<Set<BreedingEvent>> findBreedingEventsByDadBreeder(Animal dadBreeder);

    Optional<Set<BreedingEvent>> findBreedingEventsByMomBreeder(Animal momBreeder);

    Optional<Set<BreedingEvent>> findBreedingEventsByPairFormingDateGreaterThan(Long pairFormingDate);

    Optional<Set<BreedingEvent>> findBreedingEventsByPairFormingDateLessThan(Long pairFormingDate);

    Optional<Set<BreedingEvent>> findBreedingEventsByWeanedDayGreaterThan(Long weanedDate);

    Optional<Set<BreedingEvent>> findBreedingEventsByWeanedDayLessThan(Long weanedDate);



}
