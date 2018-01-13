package application.repositories.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.MethodSequence;

public interface MethodSequenceRepository extends JpaRepository<MethodSequence, Integer> {

    MethodSequence findMethodSequenceById(Integer id);
}
