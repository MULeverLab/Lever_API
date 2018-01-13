package application.repositories.method;

import application.entities.method.Method;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MethodRepository extends JpaRepository<Method, Integer> {

    Method findMethodById (Integer id);
    Optional<List<Method>> findMethodsByIdGreaterThan (Integer Id);
    Optional<List<Method>> findMethodsByDateGreaterThan (Long date);
    Optional<List<Method>> findMethodsByDateLessThan (Long date);
    Optional<List<Method>> findMethodsByMethodType (Integer methodType);
    Optional<List<Method>> findMethodsByMethodId (Integer methodId);

}
