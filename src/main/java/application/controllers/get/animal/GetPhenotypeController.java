package application.controllers.get.animal;


import application.entities.animal.Phenotype;
import application.repositories.animal.PhenotypeRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetPhenotypeController {

    private final PhenotypeRepository phenotypeRepository;

    @Autowired
    public GetPhenotypeController(PhenotypeRepository phenotypeRepository) {
        this.phenotypeRepository = phenotypeRepository;
    }

    @GetMapping("/phenotype")
    List<Phenotype> getPhenotype (@AuthenticationPrincipal User user,
                                         @RequestParam(value = "phenotypeId", required = false) Integer phenotypeId) {

        List<Phenotype> baseList = phenotypeRepository.findAll();
        if (baseList.size() > 0) {

            if (phenotypeId != null) {
                Phenotype phenotype = phenotypeRepository.findOne(phenotypeId);
                if(phenotype != null){
                    baseList.retainAll((Collection<?>) phenotype);
                }
            }

            return baseList;
        }

        return new ArrayList<>();
    }
}
