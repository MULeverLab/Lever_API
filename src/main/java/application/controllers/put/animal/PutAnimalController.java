package application.controllers.put.animal;

import application.entities.animal.Animal;
import application.repositories.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutAnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @PostMapping("/animal")
    ResponseEntity<String> putAnimal(@RequestBody Animal animal){
        animalRepository.save(animal);
        return new ResponseEntity<String>("Animal is saved/updated", HttpStatus.OK);
        }
    }


