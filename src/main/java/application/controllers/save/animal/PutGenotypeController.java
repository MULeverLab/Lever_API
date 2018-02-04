package application.controllers.save.animal;

import application.entities.animal.Genotype;
import application.repositories.animal.GenotypeRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutGenotypeController {

        @Autowired
        GenotypeRepository genotypeRepository;

        @PostMapping("/genotype")
        ResponseEntity<String> putGenotype (@AuthenticationPrincipal User user,
                                            @RequestBody Genotype genotype){
            genotypeRepository.save(genotype);
            return new ResponseEntity<String>("Genotype is saved/updated", HttpStatus.OK);
        }
    }


