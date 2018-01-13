package application.controllers.get.animal;

import application.entities.animal.Genotype;
import application.repositories.animal.GenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/genotype")
public class GetGenotypeController {

    @Autowired
    GenotypeRepository genotypeRepository;

    @GetMapping("/genotype")
    ResponseEntity<String> getGenotype(@RequestParam(value = "genotypeId", required = false) Integer genotypeId) {

        Optional<List<Genotype>> baseOptional = genotypeRepository.findGenotypesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size() > 0) {
            List<Genotype> baseList = baseOptional.get();
            List<Genotype> filterList;

            if (genotypeId != null) {
                Genotype filterOptional = genotypeRepository.findGenotypeById(genotypeId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);
                }

            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO GENOTYPES IN BASE List", HttpStatus.NO_CONTENT);


    }

}
