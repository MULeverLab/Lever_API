package application.controllers.get.animal;

import application.entities.animal.Animal;
import application.entities.animal.Genotype;
import application.entities.animal.PhenotypeBridge;
import application.repositories.animal.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetAnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    BreedingEventRepository breedingEventRepository;

    @Autowired
    ColonyRepository colonyRepository;

    @Autowired
    GenotypeRepository genotypeRepository;

    @Autowired
    MouseRepository mouseRepository;

    @Autowired
    PhenotypeRepository phenotypeRepository;

    @Autowired
    PhenotypeBridgeRepository phenotypeBridgeRepository;

    @GetMapping("/animal")
    ResponseEntity<String> getAnimal(@RequestParam(value = "animalId", required = false) Integer animalId,
                                     @RequestParam(value = "genotypeId", required = false) Integer genotypeId,
                                     @RequestParam(value = "phenotypeId", required = false) Integer phenotypeId,
                                     @RequestParam(value = "sex", required = false) Integer sex,
                                     @RequestParam(value = "beforeDateOfBirth", required = false) Long beforeDateOfBirth,
                                     @RequestParam(value = "beforeDateOfDeath", required = false) Long beforeDateOfDeath,
                                     @RequestParam(value = "afterDateOfBirth", required = false) Long afterDateOfBirth,
                                     @RequestParam(value = "afterDateOfDeath", required = false) Long afterDateOfDeath,
                                     @RequestParam(value = "causeOfDeath", required = false) Integer causeOfDeath,
                                     @RequestParam(value = "speciesId", required = false) Integer speciesId){

        List<Animal> baseList = animalRepository.findAll();
        if (baseList.size()>0){
            List<Animal> filterList;

            //This was changed. The animalRepository method was changed from findAnimalsById to findAnimalById and the
            //return type was changed to Animal. As such I check to if the id is found, the return Animal object is not
            //null and then add that animal to a List.
            if(animalId != null){
                Animal filterOptional = animalRepository.findAnimalById(animalId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);
                }
            }

            if(genotypeId != null){
                Genotype genotypeOptional = genotypeRepository.findGenotypeById(genotypeId);
                if (genotypeOptional != null) {
                    Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByGenotype(genotypeOptional);
                    if (filterOptional.isPresent()) {
                        filterList = filterOptional.get();
                        baseList.retainAll(filterList);
                    }
                }
            }

            if(phenotypeId != null){
                Optional <List<PhenotypeBridge>> phenotypeOptional = phenotypeBridgeRepository.findPheontypeBridgeByPhenotype(phenotypeId);
                if (phenotypeOptional.isPresent()) {
                    filterList = new ArrayList<>();

                    for(PhenotypeBridge phenotypeBridge : phenotypeOptional.get()){
                        Optional<Animal> animalOptional = animalRepository.findAnimalByPhenotypeBridgeSetContaining(phenotypeBridge);
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
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsBySex(sex);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }


            if(beforeDateOfBirth != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByDateOfBirthLessThan(beforeDateOfBirth);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if(afterDateOfBirth != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByDateOfBirthGreaterThan(afterDateOfBirth);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if(beforeDateOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByDateOfDeathLessThan(beforeDateOfDeath);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if(afterDateOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByDateOfDeathGreaterThan(afterDateOfDeath);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if(causeOfDeath != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsByCauseOfDeath(causeOfDeath);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if(speciesId != null){
                Optional<List<Animal>> filterOptional = animalRepository.findAnimalsBySpecies(speciesId);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
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

        return new ResponseEntity<>("NO ANIMALS IN BASE List", HttpStatus.NO_CONTENT);
    }






}
