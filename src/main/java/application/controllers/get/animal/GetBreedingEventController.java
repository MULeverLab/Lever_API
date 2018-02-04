package application.controllers.get.animal;

import application.entities.animal.Animal;
import application.entities.animal.BreedingEvent;
import application.repositories.animal.AnimalRepository;
import application.repositories.animal.BreedingEventRepository;
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
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetBreedingEventController {

    @Autowired
    BreedingEventRepository breedingEventRepository;

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/breedingevent")
    ResponseEntity<String> getBreedingEvent(@AuthenticationPrincipal User user,
                                            @RequestParam(value = "breedingEventTd", required = false) Integer breedingEventId,
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
                    baseList.retainAll((Collection<?>) filterOptional);
                }
            }

            if(dadBreederId != null){
                Animal dad = animalRepository.findAnimalById(dadBreederId);
                if (dad != null){
                    Optional < List<BreedingEvent>> filterOptional =
                            breedingEventRepository.findBreedingEventsByDadBreeder(dad);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if (momBreederId != null){
                Animal mom = animalRepository.findAnimalById(momBreederId);
                if (mom != null){
                    Optional < List<BreedingEvent>> filterOptional =
                            breedingEventRepository.findBreedingEventsByMomBreeder(mom);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            if (afterPairFormingDate != null){
                Optional<List<BreedingEvent>> filterOptional =
                        breedingEventRepository.findBreedingEventsByPairFormingDateGreaterThan(afterPairFormingDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (beforePairFormingDate != null){
                Optional<List<BreedingEvent>> filterOptional =
                        breedingEventRepository.findBreedingEventsByPairFormingDateLessThan(beforePairFormingDate);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (afterWeanedDay != null){
                Optional<List<BreedingEvent>> filterOptional =
                        breedingEventRepository.findBreedingEventsByWeanedDayGreaterThan(afterWeanedDay);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (beforeWeanedDay != null){
                Optional<List<BreedingEvent>> filterOptional;
                filterOptional = breedingEventRepository.findBreedingEventsByWeanedDayLessThan(beforeWeanedDay);
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

        return new ResponseEntity<>("NO BREEDING EVENT IN BASE LIST", HttpStatus.NO_CONTENT);

    }
}
