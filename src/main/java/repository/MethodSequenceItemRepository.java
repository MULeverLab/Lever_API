package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.MethodSequenceItem;

public interface MethodSequenceItemRepository extends JpaRepository<MethodSequenceItem, Integer> {

}
