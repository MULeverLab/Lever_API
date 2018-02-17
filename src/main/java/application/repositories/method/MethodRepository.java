package application.repositories.method;

import application.entities.method.Method;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MethodRepository extends JpaRepository<Method, Integer> {

    Optional<List<Method>> findByDateGreaterThan(Long date);

    Optional<List<Method>> findByDateLessThan(Long date);

    Optional<List<Method>> findByMethodType(Integer methodType);

    Optional<List<Method>> findByMethodId(Integer methodId);

}
