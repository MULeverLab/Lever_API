package application.controllers.get.animal;


import application.entities.animal.Phenotype;
import application.repositories.animal.PhenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetPhenotypeController {

    @Autowired
    PhenotypeRepository phenotypeRepository;

    @GetMapping("/phenotype")
    ResponseEntity<String> getPhenotype (@RequestParam(value = "phenotypeId", required = false) Integer phenotypeId) {

        Optional<List<Phenotype>> baseOptional = phenotypeRepository.findPhenotypesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size() > 0) {
            List<Phenotype> baseList = baseOptional.get();
            List<Phenotype> filterList;

            if (phenotypeId != null) {
                Phenotype filterOptional = phenotypeRepository.findPhenotypeById(phenotypeId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);
                }

            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO PHENOTYPES IN BASE List", HttpStatus.NO_CONTENT);

    }
}
