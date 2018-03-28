package application.controllers.get.animal;

import application.entities.animal.Animal;
import application.entities.animal.Genotype;
import application.entities.animal.PhenotypeBridge;
import application.repositories.animal.*;
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
@RequestMapping("/get/animal")
public class GetAnimalController {

    private final AnimalRepository animalRepository;
    private final GenotypeRepository genotypeRepository;
    private final PhenotypeBridgeRepository phenotypeBridgeRepository;

    @Autowired
    public GetAnimalController(AnimalRepository animalRepository, GenotypeRepository genotypeRepository, PhenotypeBridgeRepository phenotypeBridgeRepository) {
        this.animalRepository = animalRepository;
        this.genotypeRepository = genotypeRepository;
        this.phenotypeBridgeRepository = phenotypeBridgeRepository;
    }

    @GetMapping("/animal")
    List<Animal> getAnimal(@AuthenticationPrincipal User user,
                                     @RequestParam(value = "animalId", required = false) Integer animalId,
                                     @RequestParam(value = "genotypeId", required = false) Integer genotypeId,
                                     @RequestParam(value = "phenotypeId", required = false) Integer phenotypeId,
                                     @RequestParam(value = "sex", required = false) Integer sex,
                                     @RequestParam(value = "beforeDateOfBirth", required = false) Long beforeDateOfBirth,
                                     @RequestParam(value = "beforeDateOfDeath", required = false) Long beforeDateOfDeath,
                                     @RequestParam(value = "afterDateOfBirth", required = false) Long afterDateOfBirth,
                                     @RequestParam(value = "afterDateOfDeath", required = false) Long afterDateOfDeath,
                                     @RequestParam(value = "causeOfDeath", required = false) Integer causeOfDeath){

        List<Animal> baseList = animalRepository.findAll();
        if (baseList.size() > 0){

            if(animalId != null){
                Animal animal = animalRepository.findOne(animalId);
                if(animal != null){
                    baseList.retainAll((Collection<?>) animal);
                }
            }

            if(genotypeId != null){
                Genotype genotype = genotypeRepository.findOne(genotypeId);
                if (genotype != null) {
                    Optional<List<Animal>> filterOptional = animalRepository.findByGenotype(genotype);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if(phenotypeId != null){
                Optional <List<PhenotypeBridge>> phenotypeOptional = phenotypeBridgeRepository.findByPhenotype(phenotypeId);
                if (phenotypeOptional.isPresent()) {
                    ArrayList<Animal> filterList = new ArrayList<>();

                    for(PhenotypeBridge phenotypeBridge : phenotypeOptional.get()){
                        Optional<Animal> animalOptional = animalRepository.findByPhenotypeBridgeSetContaining(phenotypeBridge);
                        if(animalOptional.isPresent()){
                            if(!filterList.contains(animalOptional.get())) {
                                filterList.add(animalOptional.get());
                            }
                        }
                    }

                    baseList.retainAll(filterList);
                }
            }

            if(sex != null){
                Optional<List<Animal>> filterOptional = animalRepository.findBySex(sex);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeDateOfBirth != null){
                Optional<List<Animal>> filterOptional = animalRepository.findByDateOfBirthLessThan(beforeDateOfBirth);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterDateOfBirth != null){
                Optional<List<Animal>> filterOptional = animalRepository.findByDateOfBirthGreaterThan(afterDateOfBirth);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(beforeDateOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findByDateOfDeathLessThan(beforeDateOfDeath);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(afterDateOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findByDateOfDeathGreaterThan(afterDateOfDeath);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(causeOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findByCauseOfDeath(causeOfDeath);
                filterOptional.ifPresent(baseList::retainAll);
            }

            return baseList;
        }

        return new ArrayList<>();
    }






}
