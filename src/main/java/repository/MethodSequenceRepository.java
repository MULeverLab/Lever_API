package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.MethodSequence;

public interface MethodSequenceRepository extends JpaRepository<MethodSequence, Integer> {

}
