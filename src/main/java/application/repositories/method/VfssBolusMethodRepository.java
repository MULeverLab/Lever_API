package application.repositories.method;

import application.entities.method.VfssBolusMethod;
import application.entities.method.VfssPositionOneMethod;
import application.entities.method.VfssPositionTwoMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VfssBolusMethodRepository extends JpaRepository<VfssBolusMethod, Integer> {

    VfssBolusMethod findVfssBolusMethodById (Integer Id);
    Optional<List<VfssBolusMethod>> findVfssBolusMethodsByMethodRunId (Integer methodRunId);




}
