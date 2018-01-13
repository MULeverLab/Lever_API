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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CompetencyRepository competencyRepository;
    @Autowired
    private UserCompetencyBridgeRespository userCompetencyBridgeRespository;
    @Autowired
    private ColonyRepository colonyRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MethodSequenceRepository methodSequenceRepository;

    @GetMapping("/project")
    ResponseEntity<String> getProject(@RequestParam(value = "projectId", required = false) Integer projectId,
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
                if(optional.isPresent()){
                    baseList.retainAll((Collection<?>) optional.get());
                }
            }

            if(beforeStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateLessThan(beforeStartDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(afterStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateGreaterThan(afterStartDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(beforeCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateLessThan(beforeCompletionDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(afterCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateGreaterThan(afterCompletionDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(colonyId != null){
                Colony colony = colonyRepository.findColonyById(colonyId);
                if(colony != null){
                    filterOptional = projectRepository.findProjectsByColonySetContains(colony);
                    if(filterOptional.isPresent()){
                        baseList.retainAll(filterOptional.get());
                    }
                }
            }

            if(animalId != null){
                Animal animal = animalRepository.findAnimalById(animalId);
                if(animal != null){
                    filterOptional = projectRepository.findProjectsByAnimalSetContains(animal);
                    if(filterOptional.isPresent()){
                        baseList.retainAll(filterOptional.get());
                    }
                }
            }

            if(accountId != null){
                Account account = accountRepository.findAccountById(accountId);
                if(account != null){
                    filterOptional = projectRepository.findProjectsByAccountSetContains(account);
                    if(filterOptional.isPresent()){
                        baseList.retainAll(filterOptional.get());
                    }
                }
            }

            if(methodSequenceId != null){
                MethodSequence methodSequence = methodSequenceRepository.findMethodSequenceById(methodSequenceId);
                if(methodSequence != null){
                    filterOptional = projectRepository.findProjectsByMethodSequencesContains(methodSequence);
                    if(filterOptional.isPresent()){
                        baseList.retainAll(filterOptional.get());
                    }
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO PROJECTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}
