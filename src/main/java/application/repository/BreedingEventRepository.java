package application.repository;

import application.animal.Animal;
import application.animal.BreedingEvent;
import application.animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BreedingEventRepository extends JpaRepository<BreedingEvent, Integer> {

    Optional<Set<BreedingEvent>> findBreedingEventByIdGreaterThan(Integer id);

    Optional<Set<BreedingEvent>> findBreedingEventByDadBreeder(Animal dadBreeder);

    Optional<Set<BreedingEvent>> findBreedingEventByMomBreeder(Animal momBreeder);
}
