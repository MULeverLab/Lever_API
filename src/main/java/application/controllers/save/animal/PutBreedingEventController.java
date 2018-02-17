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

    private final BreedingEventRepository breedingEventRepository;

    @Autowired
    public PutBreedingEventController(BreedingEventRepository breedingEventRepository) {
        this.breedingEventRepository = breedingEventRepository;
    }

    @PostMapping("/breedingEvent")
    ResponseEntity putBreedingEvent(@AuthenticationPrincipal User user,
                                    @RequestBody BreedingEvent breedingEvent){
        breedingEventRepository.save(breedingEvent);
        return new ResponseEntity(HttpStatus.OK);
    }
}
