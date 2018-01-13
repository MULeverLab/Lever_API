package application.repositories.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.ScheduleEvent;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Integer> {
}
