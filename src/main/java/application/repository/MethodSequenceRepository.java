package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.schedule.MethodSequence;

public interface MethodSequenceRepository extends JpaRepository<MethodSequence, Integer> {

    MethodSequence findMethodSequenceById(Integer id);
}
