package application.controllers.get.schedule;

import application.entities.animal.Animal;
import application.entities.project.Account;
import application.entities.project.Project;
import application.entities.schedule.ScheduleEvent;
import application.repositories.animal.AnimalRepository;
import application.repositories.project.AccountRepository;
import application.repositories.project.ProjectRepository;
import application.repositories.schedule.ScheduleEventRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class GetScheduleEventController {

    private final ScheduleEventRepository scheduleEventRepository;
    private final ProjectRepository projectRepository;
    private final AnimalRepository animalRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public GetScheduleEventController(ScheduleEventRepository scheduleEventRepository, ProjectRepository projectRepository, AnimalRepository animalRepository, AccountRepository accountRepository) {
        this.scheduleEventRepository = scheduleEventRepository;
        this.projectRepository = projectRepository;
        this.animalRepository = animalRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/event")
    List<ScheduleEvent> getScheduleEvent(@AuthenticationPrincipal User user,
                                            @RequestParam(value = "eventId", required = false) Integer eventId,
                                            @RequestParam(value = "projectId", required = false) Integer projectId,
                                            @RequestParam(value = "animalId", required = false) Integer animalId,
                                            @RequestParam(value = "accountId", required = false) Integer accountId,
                                            @RequestParam(value = "beforeAddedDate", required = false) Long beforeAddedDate,
                                            @RequestParam(value = "afterAddedDate", required = false) Long afterAddedDate,
                                            @RequestParam(value = "beforeDueDate", required = false) Long beforeDueDate,
                                            @RequestParam(value = "afterDueDate", required = false) Long afterDueDate,
                                            @RequestParam(value = "beforeClaimDate", required = false) Long beforeClaimDate,
                                            @RequestParam(value = "afterClaimDate", required = false) Long afterClaimDate,
                                            @RequestParam(value = "beforeCompletionDate", required = false) Long beforeCompletionDate,
                                            @RequestParam(value = "afterCompletionDate", required = false) Long afterCompletionDate){

        List<ScheduleEvent> baseList = scheduleEventRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<ScheduleEvent>> filterOptional;

            if (eventId != null) {
                ScheduleEvent scheduleEvent = scheduleEventRepository.findOne(eventId);

                if(scheduleEvent != null){
                    baseList.retainAll((Collection<?>) scheduleEvent);
                }
            }

            if (projectId != null) {
                Project project = projectRepository.findOne(projectId);

                if(project != null){
                    filterOptional = scheduleEventRepository.findByProject(project);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if (projectId != null) {
                Animal animal = animalRepository.findOne(animalId);

                if (animal != null) {
                    filterOptional = scheduleEventRepository.findByAnimal(animal);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if (accountId != null) {
                Account account = accountRepository.findOne(accountId);

                if (account != null) {
                    filterOptional = scheduleEventRepository.findByAccount(account);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if(beforeAddedDate != null){
                filterOptional = scheduleEventRepository.findByAddedDateLessThan(beforeAddedDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterAddedDate != null){
                filterOptional = scheduleEventRepository.findByAddedDateGreaterThan(afterAddedDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeDueDate != null){
                filterOptional = scheduleEventRepository.findByDueDateLessThan(beforeDueDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterDueDate != null){
                filterOptional = scheduleEventRepository.findByDueDateGreaterThan(afterDueDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeClaimDate != null){
                filterOptional = scheduleEventRepository.findByClaimDateLessThan(beforeClaimDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterClaimDate != null){
                filterOptional = scheduleEventRepository.findByClaimDateGreaterThan(afterClaimDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeCompletionDate != null){
                filterOptional = scheduleEventRepository.findByCompletionDateLessThan(beforeCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterCompletionDate != null){
                filterOptional = scheduleEventRepository.findByCompletionDateGreaterThan(afterCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            return baseList;
        }

        return new ArrayList<>();
    }
}
