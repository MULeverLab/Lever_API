package application.controllers;

import application.animal.Animal;
import application.animal.Colony;
import application.animal.Genotype;
import application.project.Account;
import application.project.Project;
import application.project.UserCompetencyBridge;
import application.repository.*;
import application.schedule.MethodSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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


        Set<Project> baseSet = (Set<Project>) projectRepository.findAll();
        if (baseSet.size() > 0){
            Optional<Set<Project>> filterOptional;

            if(projectId != null){
                Optional<Project> optional = projectRepository.findProjectById(projectId);
                if(optional.isPresent()){
                    baseSet.retainAll((Collection<?>) optional.get());
                }
            }

            if(beforeStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateLessThan(beforeStartDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseSet.retainAll(filterOptional.get());
                }
            }

            if(afterStartDate != null){
                filterOptional = projectRepository.findProjectsByStartDateGreaterThan(afterStartDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseSet.retainAll(filterOptional.get());
                }
            }

            if(beforeCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateLessThan(beforeCompletionDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseSet.retainAll(filterOptional.get());
                }
            }

            if(afterCompletionDate != null){
                filterOptional = projectRepository.findProjectsByCompletionDateGreaterThan(afterCompletionDate);
                if(filterOptional.isPresent() && filterOptional.get().size() > 0){
                    baseSet.retainAll(filterOptional.get());
                }
            }

            if(colonyId != null){
                Colony colony = colonyRepository.findColonyById(colonyId);
                if(colony != null){
                    filterOptional = projectRepository.findProjectsByColonySetContains(colony);
                    if(filterOptional.isPresent()){
                        baseSet.retainAll(filterOptional.get());
                    }
                }
            }

            if(animalId != null){
                Animal animal = animalRepository.findAnimalById(animalId);
                if(animal != null){
                    filterOptional = projectRepository.findProjectsByAnimalSetContains(animal);
                    if(filterOptional.isPresent()){
                        baseSet.retainAll(filterOptional.get());
                    }
                }
            }

            if(accountId != null){
                Account account = accountRepository.findAccountById(accountId);
                if(account != null){
                    filterOptional = projectRepository.findProjectsByAccountSetContains(account);
                    if(filterOptional.isPresent()){
                        baseSet.retainAll(filterOptional.get());
                    }
                }
            }

            if(methodSequenceId != null){
                MethodSequence methodSequence = methodSequenceRepository.findMethodSequenceById(methodSequenceId);
                if(methodSequence != null){
                    filterOptional = projectRepository.findProjectsByMethodSequencesContains(methodSequence);
                    if(filterOptional.isPresent()){
                        baseSet.retainAll(filterOptional.get());
                    }
                }
            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO PROJECTS IN BASE SET", HttpStatus.NO_CONTENT);
    }
}
