package repository;

import animal.Colony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Colony, Integer> {

}