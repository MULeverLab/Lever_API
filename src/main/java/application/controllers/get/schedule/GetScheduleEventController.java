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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class GetScheduleEventController {

    @Autowired
    private ScheduleEventRepository scheduleEventRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/event")
    ResponseEntity<String> getScheduleEvent(@AuthenticationPrincipal User user,
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

        List<ScheduleEvent> baseList = (List<ScheduleEvent>) scheduleEventRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<ScheduleEvent>> filterOptional;

            if (eventId != null) {
                Optional<ScheduleEvent> optional = scheduleEventRepository.findScheduleEventById(eventId);
                optional.ifPresent(scheduleEvent -> baseList.retainAll((Collection<?>) scheduleEvent));
            }

            if (projectId != null) {
                Optional<Project> optional = projectRepository.findProjectById(projectId);
                optional.ifPresent(project -> baseList.retainAll((Collection<?>) project));
            }

            if (projectId != null) {
                Animal animal = animalRepository.findAnimalById(animalId);
                if (animal != null) {
                    baseList.retainAll((Collection<?>) animal);
                }
            }

            if (accountId != null) {
                Account account = accountRepository.findAccountById(accountId);
                if (account != null) {
                    baseList.retainAll((Collection<?>) account);
                }
            }

            if(beforeAddedDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByAddedDateLessThan(beforeAddedDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterAddedDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByAddedDateGreaterThan(afterAddedDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeDueDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByDueDateLessThan(beforeDueDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterDueDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByDueDateGreaterThan(afterDueDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeClaimDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByClaimDateLessThan(beforeClaimDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterClaimDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByClaimDateGreaterThan(afterClaimDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeCompletionDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByCompletionDateLessThan(beforeCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterCompletionDate != null){
                filterOptional = scheduleEventRepository.findScheduleEventsByCompletionDateGreaterThan(afterCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO PROJECTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}
