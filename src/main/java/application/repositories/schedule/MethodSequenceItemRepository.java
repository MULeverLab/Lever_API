package application.repositories.schedule;

import application.entities.project.Competency;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.MethodSequenceItem;

import java.util.List;
import java.util.Optional;

public interface MethodSequenceItemRepository extends JpaRepository<MethodSequenceItem, Integer> {

    Optional<MethodSequenceItem> findMethodSequenceItemById(Integer id);

    Optional<List<MethodSequenceItem>> findMethodSequenceItemsByMethodType(Integer methodType);

    Optional<List<MethodSequenceItem>> findMethodSequenceItemsByCompetency(Competency competency);

    Optional<List<MethodSequenceItem>> findMethodSequenceItemsByLevel(Integer level);
}
