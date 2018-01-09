package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
