package application.controllers.put.animal;

import application.entities.animal.Genotype;
import application.repositories.animal.GenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutGenotypeController {

        @Autowired
        GenotypeRepository genotypeRepository;

        @PostMapping("/genotype")
        ResponseEntity<String> putGenotype (@RequestBody Genotype genotype){
            genotypeRepository.save(genotype);
            return new ResponseEntity<String>("Genotype is saved/updated", HttpStatus.OK);
        }
    }


