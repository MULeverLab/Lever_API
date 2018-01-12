package application.repository;

import application.animal.Animal;
import application.animal.Colony;
import application.project.Account;
import application.schedule.MethodSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import application.project.Project;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findProjectById(Integer id);

    Optional<Set<Project>> findProjectsByStartDateLessThan(Long startDate);

    Optional<Set<Project>> findProjectsByStartDateGreaterThan(Long startDate);

    Optional<Set<Project>> findProjectsByCompletionDateLessThan(Long completionDate);

    Optional<Set<Project>> findProjectsByCompletionDateGreaterThan(Long completionDate);

    Optional<Set<Project>> findProjectsByColonySetContains(Colony colony);

    Optional<Set<Project>> findProjectsByAnimalSetContains(Animal animal);

    Optional<Set<Project>> findProjectsByAccountSetContains(Account account);

    Optional<Set<Project>> findProjectsByMethodSequencesContains(MethodSequence methodSequence);
}
