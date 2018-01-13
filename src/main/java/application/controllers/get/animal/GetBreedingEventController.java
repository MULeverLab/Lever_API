package application.controllers.get.animal;

import application.entities.animal.Animal;
import application.entities.animal.BreedingEvent;
import application.repositories.animal.AnimalRepository;
import application.repositories.animal.BreedingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

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

        Optional<List<BreedingEvent>> baseOptional = breedingEventRepository.findBreedingEventByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            List<BreedingEvent> baseList = baseOptional.get();
            List<BreedingEvent> filterList;


            //Get Breeding Event by ID
            if (breedingEventId != null){
                BreedingEvent filterOptional = breedingEventRepository.findBreedingEventById(breedingEventId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);
                }
            }

            if(dadBreederId != null){
                Animal dad = animalRepository.findAnimalById(dadBreederId);
                if (dad != null){
                    Optional < List<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByDadBreeder(dad);
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
             }

            }

            if (momBreederId != null){
                Animal mom = animalRepository.findAnimalById(momBreederId);
                if (mom != null){
                    Optional < List<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByMomBreeder(mom);
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }

            }

            if (afterPairFormingDate != null){
                Optional<List<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByPairFormingDateGreaterThan
                        (afterPairFormingDate);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (beforePairFormingDate != null){
                Optional<List<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByPairFormingDateLessThan(beforePairFormingDate);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (afterWeanedDay != null){
                Optional<List<BreedingEvent>> filterOptional = breedingEventRepository.findBreedingEventsByWeanedDayGreaterThan(afterWeanedDay);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (beforeWeanedDay != null){
                Optional<List<BreedingEvent>> filterOptional;
                filterOptional = breedingEventRepository.findBreedingEventsByWeanedDayLessThan(beforeWeanedDay);
                if(filterOptional.isPresent()){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO BREEDING EVENT IN BASE List", HttpStatus.NO_CONTENT);

    }
}
