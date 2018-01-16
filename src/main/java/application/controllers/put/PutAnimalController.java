package application.controllers.put;

import application.entities.animal.Animal;
import application.repositories.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutAnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @PostMapping("/animal")
    ResponseEntity<String> putAnimal(@RequestBody Animal animal){

        animal.getGenotype();
        animal.getPhenotypeBridgeSet();
        animal.getCauseOfDeath();
        animal.getDateOfBirth();
        animal.getDateOfDeath();
        animal.getSpecies();
        animal.getSpeciesId();
        animal.getSex();

        animalRepository.save(animal);


        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }
    }

        //if property exists place in an animal object
        //save the animal object



