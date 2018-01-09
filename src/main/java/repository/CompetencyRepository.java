package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Competency;

public interface CompetencyRepository extends JpaRepository<Competency, Integer> {
}
