package application.controllers;

import application.animal.Animal;
import application.animal.BreedingEvent;
import application.repository.AnimalRepository;
import application.repository.BreedingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/breedingevent")
public class GetBreedingEventController {

    @Autowired
    BreedingEventRepository breedingEventRepository;

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/breedingevent")
    ResponseEntity<String> getBreedingEvent(@RequestParam(value = "breedingEventTd", required = false) Integer breedingEventId,
                                            @RequestParam(value = "dadBreederId", required = false) Integer dadBreederId,
                                            @RequestParam(value = "momBreederId", required = false) Integer momBreederId,
                                            @RequestParam(value = "afterPairFormingDate", required = false) Long afterPairFormingDate,
                                            @RequestParam(value = "beforePairFormingDate", required = false) Long beforePairFormingDate,
                                            @RequestParam(value = "afterWeanedDay", required = false) Long afterWeanedDay,
                                            @RequestParam(value = "beforeWeanedDay", required = false) Long beforeWeanedDay){

        Optional<Set<BreedingEvent>> baseOptional = breedingEventRepository.findBreedingEventByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            Set<BreedingEvent> baseSet = baseOptional.get();
            Set<BreedingEvent> filterSet;


            //Get Breeding Event by ID
            if (breedingEventId != null){
                BreedingEvent filterOptional = breedingEventRepository.findBreedingEventById(breedingEventId);
                if(filterOptional != null){
                    filterSet = new HashSet<BreedingEvent>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }
            }

            if(dadBreederId != null){
                Animal dad = animalRepository.findAnimalById(dadBreederId);
                if (dad != null){
                    Optional < Set<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByDadBreeder(dad);
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
             }

            }

            if (momBreederId != null){
                Animal mom = animalRepository.findAnimalById(momBreederId);
                if (mom != null){
                    Optional < Set<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByMomBreeder(mom);
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }

            }

            if (afterPairFormingDate != null){
                Optional<Set<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByPairFormingDateGreaterThan
                        (afterPairFormingDate);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (beforePairFormingDate != null){
                Optional<Set<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByPairFormingDateLessThan(beforePairFormingDate);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (afterWeanedDay != null){
                Optional<Set<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByWeanedDayGreaterThan(afterWeanedDay);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (beforeWeanedDay != null){
                Optional<Set<BreedingEvent>> filterOptional;
                filterOptional = breedingEventRepository.findBreedingEventsByWeanedDayLessThan(beforeWeanedDay);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO BREEDING EVENT IN BASE SET", HttpStatus.NO_CONTENT);

    }
}
