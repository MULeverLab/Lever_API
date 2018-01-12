package application.controllers;

import application.animal.Genotype;
import application.repository.GenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/genotype")
public class GetGenotypeController {

    @Autowired
    GenotypeRepository genotypeRepository;

    @GetMapping("/genotype")
    ResponseEntity<String> getGenotype(@RequestParam(value = "genotypeId", required = false) Integer genotypeId) {

        Optional<Set<Genotype>> baseOptional = genotypeRepository.findGenotypesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size() > 0) {
            Set<Genotype> baseSet = baseOptional.get();
            Set<Genotype> filterSet;

            if (genotypeId != null) {
                Genotype filterOptional = genotypeRepository.findGenotypeById(genotypeId);
                if(filterOptional != null){
                    filterSet = new HashSet<Genotype>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }

            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO GENOTYPES IN BASE SET", HttpStatus.NO_CONTENT);


    }

}
