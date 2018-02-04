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

    @Autowired
    PhenotypeRepository phenotypeRepository;

    @GetMapping("/phenotype")
    ResponseEntity<String> getPhenotype (@AuthenticationPrincipal User user,
                                         @RequestParam(value = "phenotypeId", required = false) Integer phenotypeId) {

        Optional<List<Phenotype>> baseOptional = phenotypeRepository.findPhenotypesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size() > 0) {
            List<Phenotype> baseList = baseOptional.get();

            if (phenotypeId != null) {
                Phenotype filterOptional = phenotypeRepository.findPhenotypeById(phenotypeId);
                if(filterOptional != null){
                    baseList.retainAll((Collection<?>) filterOptional);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO PHENOTYPES IN BASE List", HttpStatus.NO_CONTENT);

    }
}
