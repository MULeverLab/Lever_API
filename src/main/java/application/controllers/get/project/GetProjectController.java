package application.controllers.get.project;

import application.entities.animal.Animal;
import application.entities.animal.Colony;
import application.entities.project.Account;
import application.entities.project.Project;
import application.repositories.animal.AnimalRepository;
import application.repositories.animal.ColonyRepository;
import application.repositories.project.AccountRepository;
import application.repositories.project.CompetencyRepository;
import application.repositories.project.ProjectRepository;
import application.repositories.project.UserCompetencyBridgeRespository;
import application.repositories.schedule.MethodSequenceRepository;
import application.entities.schedule.MethodSequence;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class GetProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ColonyRepository colonyRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MethodSequenceRepository methodSequenceRepository;

    @GetMapping("/project")
    ResponseEntity<String> getProject(@AuthenticationPrincipal User user,
                                      @RequestParam(value = "projectId", required = false) Integer projectId,
                                      @RequestParam(value = "beforeStartDate", required = false) Long beforeStartDate,
                                      @RequestParam(value = "afterStartDate", required = false) Long afterStartDate,
                                      @RequestParam(value = "beforeCompletionDate", required = false) Long beforeCompletionDate,
                                      @RequestParam(value = "afterCompletionDate", required = false) Long afterCompletionDate,
                                      @RequestParam(value = "colonyId", required = false) Integer colonyId,
                                      @RequestParam(value = "animalId", required = false) Integer animalId,
                                      @RequestParam(value = "accountId", required = false) Integer accountId,
                                      @RequestParam(value = "methodSequenceId", required = false) Integer methodSequenceId){

        List<Project> baseList = (List<Project>) projectRepository.findAll();
        if (baseList.size() > 0){
            Optional<List<Project>> filterOptional;

            if(projectId != null){
                Optional<Project> optional = projectRepository.findProjectById(projectId);
                optional.ifPresent(project -> baseList.retainAll((Collection<?>) project));
            }

            if(beforeStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateLessThan(beforeStartDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateGreaterThan(afterStartDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateLessThan(beforeCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateGreaterThan(afterCompletionDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(colonyId != null){
                Colony colony = colonyRepository.findColonyById(colonyId);
                if(colony != null){
                    filterOptional = projectRepository.findProjectsByColonyListContains(colony);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if(animalId != null){
                Animal animal = animalRepository.findAnimalById(animalId);
                if(animal != null){
                    filterOptional = projectRepository.findProjectsByAnimalListContains(animal);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if(accountId != null){
                Account account = accountRepository.findAccountById(accountId);
                if(account != null){
                    filterOptional = projectRepository.findProjectsByAccountListContains(account);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if(methodSequenceId != null){
                Optional<MethodSequence> optional = methodSequenceRepository.findMethodSequenceById(methodSequenceId);
                if(optional.isPresent()){
                    filterOptional = projectRepository.findProjectsByMethodSequencesContains(optional.get());
                    filterOptional.ifPresent(baseList::retainAll);
                }
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
