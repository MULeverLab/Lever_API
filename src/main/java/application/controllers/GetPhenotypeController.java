package application.controllers;


import application.animal.Phenotype;
import application.repository.PhenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/phenotype")
public class GetPhenotypeController {

    @Autowired
    PhenotypeRepository phenotypeRepository;

    @GetMapping("/phenotype")
    ResponseEntity<String> getPhenotype (@RequestParam(value = "phenotypeId", required = false) Integer phenotypeId) {

        Optional<Set<Phenotype>> baseOptional = phenotypeRepository.findPhenotypesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size() > 0) {
            Set<Phenotype> baseSet = baseOptional.get();
            Set<Phenotype> filterSet;

            if (phenotypeId != null) {
                Phenotype filterOptional = phenotypeRepository.findPhenotypeById(phenotypeId);
                if(filterOptional != null){
                    filterSet = new HashSet<Phenotype>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }

            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO PHENOTYPES IN BASE SET", HttpStatus.NO_CONTENT);

    }
}
