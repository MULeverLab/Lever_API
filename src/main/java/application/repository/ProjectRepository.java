package application.repository;

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

    Optional<Set<Project>>
}
