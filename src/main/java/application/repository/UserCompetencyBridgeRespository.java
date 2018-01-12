package application.repository;

import application.project.UserCompetencyBridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompetencyBridgeRespository extends JpaRepository<UserCompetencyBridge, Integer> {
}