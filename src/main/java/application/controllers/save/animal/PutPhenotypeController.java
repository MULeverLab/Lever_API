package application.controllers.save.animal;

import application.entities.animal.Phenotype;
import application.repositories.animal.PhenotypeRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutPhenotypeController {

    private final PhenotypeRepository phenotypeRepository;

    @Autowired
    public PutPhenotypeController(PhenotypeRepository phenotypeRepository) {
        this.phenotypeRepository = phenotypeRepository;
    }

    @PostMapping("/phenotype")
    ResponseEntity putPhenotype (@AuthenticationPrincipal User user,
                                 @RequestBody Phenotype phenotype){
        phenotypeRepository.save(phenotype);
        return new ResponseEntity(HttpStatus.OK);
    }
}
