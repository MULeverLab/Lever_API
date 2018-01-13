package application.repositories.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.MethodSequenceItem;

public interface MethodSequenceItemRepository extends JpaRepository<MethodSequenceItem, Integer> {

}
