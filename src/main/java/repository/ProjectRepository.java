package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
