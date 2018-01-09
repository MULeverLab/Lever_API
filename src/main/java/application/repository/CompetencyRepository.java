package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.project.Competency;

public interface CompetencyRepository extends JpaRepository<Competency, Integer> {
}
