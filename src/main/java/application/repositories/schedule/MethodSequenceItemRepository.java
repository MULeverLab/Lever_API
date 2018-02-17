package application.repositories.schedule;

import application.entities.project.Competency;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.MethodSequenceItem;

import java.util.List;
import java.util.Optional;

public interface MethodSequenceItemRepository extends JpaRepository<MethodSequenceItem, Integer> {

    Optional<List<MethodSequenceItem>> findByMethodType(Integer methodType);

    Optional<List<MethodSequenceItem>> findByCompetency(Competency competency);

    Optional<List<MethodSequenceItem>> findByLevel(Integer level);

    Optional<List<MethodSequenceItem>> findByCompetencyAndLevel(Competency competency, Integer level);
}
