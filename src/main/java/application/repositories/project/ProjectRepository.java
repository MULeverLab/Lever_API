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

    Optional<Project> findProjectById(Integer id);

    Optional<List<Project>> findProjectsByStartDateLessThan(Long startDate);

    Optional<List<Project>> findProjectsByStartDateGreaterThan(Long startDate);

    Optional<List<Project>> findProjectsByCompletionDateLessThan(Long completionDate);

    Optional<List<Project>> findProjectsByCompletionDateGreaterThan(Long completionDate);

    Optional<List<Project>> findProjectsByColonySetContains(Colony colony);

    Optional<List<Project>> findProjectsByAnimalSetContains(Animal animal);

    Optional<List<Project>> findProjectsByAccountSetContains(Account account);

    Optional<List<Project>> findProjectsByMethodSequencesContains(MethodSequence methodSequence);
}
