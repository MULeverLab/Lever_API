package application.repositories.method;

import application.entities.method.Method;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodRepository extends JpaRepository<Method, Integer> {
}
