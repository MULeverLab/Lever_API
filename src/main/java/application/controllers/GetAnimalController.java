package application.controllers;

import application.animal.Animal;
import application.animal.Genotype;
import application.animal.PhenotypeBridge;
import application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

        Optional<Set<Animal>> baseOptional = animalRepository.findAnimalsByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0){
            Set<Animal> baseSet = baseOptional.get();
            Set<Animal> filterSet;


            //This was changed. The animalRepository method was changed from findAnimalsById to findAnimalById and the
            //return type was changed to Animal. As such I check to if the id is found, the return Animal object is not
            //null and then add that animal to a set.
            if(animalId != null){
                Animal filterOptional = animalRepository.findAnimalById(animalId);
                if(filterOptional != null){
                    filterSet = new HashSet<Animal>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }
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


            if(beforeDateOfBirth != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByDateOfBirthLessThan(beforeDateOfBirth);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if(afterDateOfBirth != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByDateOfBirthGreaterThan(afterDateOfBirth);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if(beforeDateOfDeath != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByDateOfDeathLessThan(beforeDateOfDeath);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if(afterDateOfDeath != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByDateOfDeathGreaterThan(afterDateOfDeath);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if(causeOfDeath != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsByCauseOfDeath(causeOfDeath);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if(speciesId != null){
                Optional<Set<Animal>> filterOptional = animalRepository.findAnimalsBySpecies(speciesId);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO ANIMALS IN BASE SET", HttpStatus.NO_CONTENT);
    }






}
