package repository;

import animal.BreedingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedingEventRepository extends JpaRepository<BreedingEvent, Integer> {
}
