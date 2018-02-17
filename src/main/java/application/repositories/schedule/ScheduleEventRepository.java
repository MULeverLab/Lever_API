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

    Optional<List<ScheduleEvent>> findByMethod(Method method);

    Optional<List<ScheduleEvent>> findByProject(Project project);

    Optional<List<ScheduleEvent>> findByAnimal(Animal animal);

    Optional<List<ScheduleEvent>> findByAccount(Account account);

    Optional<List<ScheduleEvent>> findByAddedDateLessThan(Long addedDate);

    Optional<List<ScheduleEvent>> findByAddedDateGreaterThan(Long addedDate);

    Optional<List<ScheduleEvent>> findByDueDateLessThan(Long dueDate);

    Optional<List<ScheduleEvent>> findByDueDateGreaterThan(Long dueDate);

    Optional<List<ScheduleEvent>> findByClaimDateLessThan(Long claimDate);

    Optional<List<ScheduleEvent>> findByClaimDateGreaterThan(Long claimDate);

    Optional<List<ScheduleEvent>> findByCompletionDateLessThan(Long completionDate);

    Optional<List<ScheduleEvent>> findByCompletionDateGreaterThan(Long completionDate);

    Optional<List<ScheduleEvent>> findByCompleteEquals(boolean complete);
}
