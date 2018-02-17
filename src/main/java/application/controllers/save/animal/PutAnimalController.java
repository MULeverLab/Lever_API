package application.controllers.save.animal;

import application.entities.animal.Animal;
import application.repositories.animal.AnimalRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutAnimalController {

    private final AnimalRepository animalRepository;

    @Autowired
    public PutAnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping("/animal")
    ResponseEntity putAnimal(@AuthenticationPrincipal User user,
                             @RequestBody Animal animal){
        animalRepository.save(animal);
        return new ResponseEntity(HttpStatus.OK);
    }
}


