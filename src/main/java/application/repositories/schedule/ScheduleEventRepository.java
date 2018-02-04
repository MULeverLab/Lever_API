package application.repositories.schedule;

import application.entities.animal.Animal;
import application.entities.method.Method;
import application.entities.project.Account;
import application.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import application.entities.schedule.ScheduleEvent;

import java.util.List;
import java.util.Optional;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Integer> {

    Optional<ScheduleEvent> findScheduleEventById(Integer id);

    Optional<List<ScheduleEvent>> findScheduleEventsByMethod(Method method);

    Optional<List<ScheduleEvent>> findScheduleEventsByProject(Project project);

    Optional<List<ScheduleEvent>> findScheduleEventsByAnimal(Animal animal);

    Optional<List<ScheduleEvent>> findScheduleEventsByAccount(Account account);

    Optional<List<ScheduleEvent>> findScheduleEventsByAddedDateLessThan(Long addedDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByAddedDateGreaterThan(Long addedDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByDueDateLessThan(Long dueDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByDueDateGreaterThan(Long dueDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByClaimDateLessThan(Long claimDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByClaimDateGreaterThan(Long claimDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByCompletionDateLessThan(Long completionDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByCompletionDateGreaterThan(Long completionDate);

    Optional<List<ScheduleEvent>> findScheduleEventsByCompleteEquals(boolean complete);
}
