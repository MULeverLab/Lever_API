package application.repositories.method;

import application.entities.method.VfssPositionOneMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VfssPositionOneMethodRepository extends JpaRepository<VfssPositionOneMethod, Integer> {

    VfssPositionOneMethod findVfssPositionOneMethodById (Integer Id);
    Optional<List<VfssPositionOneMethod>> findVfssPositionOneMethodsByMethodRunId (Integer Id);
}
