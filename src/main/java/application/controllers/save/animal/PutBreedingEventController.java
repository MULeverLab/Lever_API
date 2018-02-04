package application.controllers.save.animal;

import application.entities.animal.BreedingEvent;
import application.repositories.animal.BreedingEventRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutBreedingEventController {



        @Autowired
        BreedingEventRepository breedingEventRepository;

        @PostMapping("/breedingEvent")
        ResponseEntity<String> putBreedingEvent(@AuthenticationPrincipal User user,
                                                @RequestBody BreedingEvent breedingEvent){
            breedingEventRepository.save(breedingEvent);
            return new ResponseEntity<String>("BreedingEvent is saved/updated", HttpStatus.OK);
        }
    }


