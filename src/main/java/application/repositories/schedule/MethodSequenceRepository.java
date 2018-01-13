package application.repositories.schedule;

import application.entities.schedule.MethodSequenceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.MethodSequence;

import java.util.List;
import java.util.Optional;

public interface MethodSequenceRepository extends JpaRepository<MethodSequence, Integer> {

    Optional<MethodSequence> findMethodSequenceById(Integer id);

    Optional<List<MethodSequence>> findMethodSequencesByMethodSequenceItemListContains(MethodSequenceItem methodSequenceItem);
}
