package application.repository;

import application.method.VfssPositionOneMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VfssMethodRepository extends JpaRepository<VfssPositionOneMethod, Integer> {
}
