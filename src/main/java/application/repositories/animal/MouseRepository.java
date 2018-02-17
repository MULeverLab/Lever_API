package application.repositories.animal;

import application.entities.animal.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MouseRepository extends JpaRepository<Mouse, Integer> {

    Optional<List<Mouse>> findByCageId(Integer cageId);

    Optional<List<Mouse>> findByCoatColor(Integer coatColor);

    Optional<List<Mouse>> findByLeftEarPunches(Integer leftEarPunches);

    Optional<List<Mouse>> findByRightEarPunches(Integer rightEarPunches);
}
