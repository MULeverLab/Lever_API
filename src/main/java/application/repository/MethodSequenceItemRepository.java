package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.schedule.MethodSequenceItem;

public interface MethodSequenceItemRepository extends JpaRepository<MethodSequenceItem, Integer> {

}
