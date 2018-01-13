package application.repositories.project;

import application.entities.project.Competency;
import application.entities.project.UserCompetencyBridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCompetencyBridgeRespository extends JpaRepository<UserCompetencyBridge, Integer> {

    UserCompetencyBridge findUserCompetencyBridgeById(Integer id);

    Optional<List<UserCompetencyBridge>> findUserCompetencyBridgesByCompetency(Competency competency);
}
