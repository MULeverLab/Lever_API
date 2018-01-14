package application.repositories.method;

import application.entities.method.VfssMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VfssMethodRepository extends JpaRepository <VfssMethod, Integer> {

    VfssMethod findVfssMethodById (Integer id );
}
