package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.schedule.ScheduleEvent;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Integer> {
}
