package application.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.project.Competency;

public interface CompetencyRepository extends JpaRepository<Competency, Integer> {

    Competency findCompetencyById(Integer id);
}
