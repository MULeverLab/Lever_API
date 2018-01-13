package application.repositories.method;

import application.entities.method.VfssPositionOneMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VfssMethodRepository extends JpaRepository<VfssPositionOneMethod, Integer> {
}
