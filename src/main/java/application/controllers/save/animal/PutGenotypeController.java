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

    private final GenotypeRepository genotypeRepository;

    @Autowired
    public PutGenotypeController(GenotypeRepository genotypeRepository) {
        this.genotypeRepository = genotypeRepository;
    }

    @PostMapping("/genotype")
    ResponseEntity putGenotype (@AuthenticationPrincipal User user,
                                @RequestBody Genotype genotype){
        genotypeRepository.save(genotype);
        return new ResponseEntity(HttpStatus.OK);
    }
}


