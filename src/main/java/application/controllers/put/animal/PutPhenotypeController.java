package application.controllers.put.animal;

import application.entities.animal.Phenotype;
import application.repositories.animal.PhenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutPhenotypeController {

    @Autowired
    PhenotypeRepository phenotypeRepository;

    @PostMapping("/phenotype")
    ResponseEntity<String> putPhenotype (@RequestBody Phenotype phenotype){
        phenotypeRepository.save(phenotype);
        return new ResponseEntity<String>("Phenotype is saved/updated", HttpStatus.OK);
    }
}
