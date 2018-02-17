package application.repositories.project;

import application.entities.animal.Animal;
import application.entities.animal.Colony;
import application.entities.project.Account;
import application.entities.schedule.MethodSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.project.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<List<Project>> findByStartDateLessThan(Long startDate);

    Optional<List<Project>> findByStartDateGreaterThan(Long startDate);

    Optional<List<Project>> findByCompletionDateLessThan(Long completionDate);

    Optional<List<Project>> findByCompletionDateGreaterThan(Long completionDate);

    Optional<List<Project>> findByColonyListContains(Colony colony);

    Optional<List<Project>> findByAnimalListContains(Animal animal);

    Optional<List<Project>> findByAccountListContains(Account account);

    Optional<List<Project>> findByMethodSequencesContains(MethodSequence methodSequence);
}
