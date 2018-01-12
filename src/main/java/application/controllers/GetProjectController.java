package application.controllers;

import application.project.Account;
import application.project.Project;
import application.project.UserCompetencyBridge;
import application.repository.AccountRepository;
import application.repository.CompetencyRepository;
import application.repository.ProjectRepository;
import application.repository.UserCompetencyBridgeRespository;
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

    @GetMapping("/project")
    ResponseEntity<String> getProject(@RequestParam(value = "projectId", required = false) Integer projectId,
                                      @RequestParam(value = "beforeStartDate", required = false) Long beforeStartDate,
                                      @RequestParam(value = "afterStartDate", required = false) Long afterStartDate,
                                      @RequestParam(value = "beforeCompletionDate", required = false) Long beforeCompletionDate,
                                      @RequestParam(value = "afterComplettionDate", required = false) Long afterComplettionDate,
                                      @RequestParam(value = "colonyId", required = false) Integer colonyId,
                                      @RequestParam(value = "animalId", required = false) Integer animalId,
                                      @RequestParam(value = "accountId", required = false) Integer accountId,
                                      @RequestParam(value = "methodSequenceId", required = false) Integer methodSequenceId){


        Set<Project> baseSet = (Set<Project>) projectRepository.findAll();
        if (baseSet.size() > 0){
            Optional<Project> filterOptional;
            Set<Project> filterSet;

            if(projectId != null){
                filterOptional = projectRepository.findProjectById(projectId);
                if(filterOptional.isPresent()){
                    Project project = filterOptional.get();
                    baseSet.retainAll((Collection<?>) project);
                }
            }

            if(beforeStartDate != null){

            }

            if(genotypeId != null){
                Genotype genotypeOptional = genotypeRepository.findGenotypeById(genotypeId);
                if (genotypeOptional != null) {
                    Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByGenotype(genotypeOptional);
                    if (filterOptional.isPresent()) {
                        filterSet = filterOptional.get();
                        baseSet.retainAll(filterSet);
                    }
                }
            }

            if(phenotypeId != null){
                Optional <Set<PhenotypeBridge>> phenotypeOptional = phenotypeBridgeRepository.findPheontypeBridgeByPhenotype(phenotypeId);
                if (phenotypeOptional.isPresent()) {
                    filterSet = new HashSet<Animal>();

                    for(PhenotypeBridge phenotypeBridge : phenotypeOptional.get()){
                        Optional<Animal> animalOptional = animalRepository.findAnimalByPhenotypeBridgeSetContaining(phenotypeBridge);
                        if(animalOptional.isPresent()){
                            if(!filterSet.contains(animalOptional.get())) {
                                filterSet.add(animalOptional.get());
                            }
                        }
                    }

                    baseSet.retainAll(filterSet);
                }
            }

            if(sex != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsBySex(sex);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }


            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO PROJECTS IN BASE SET", HttpStatus.NO_CONTENT);
    }
}
