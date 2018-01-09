package application.repository;

import application.animal.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouseRepository extends JpaRepository<Mouse, Integer> {
}
