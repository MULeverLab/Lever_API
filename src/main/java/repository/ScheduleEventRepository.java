package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.ScheduleEvent;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Integer> {
}
