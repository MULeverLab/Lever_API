package application.controllers.get.animal;

import application.entities.animal.Genotype;
import application.repositories.animal.GenotypeRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetGenotypeController {

    private final GenotypeRepository genotypeRepository;

    @Autowired
    public GetGenotypeController(GenotypeRepository genotypeRepository) {
        this.genotypeRepository = genotypeRepository;
    }

    @GetMapping("/genotype")
    ResponseEntity<String> getGenotype(@AuthenticationPrincipal User user,
                                       @RequestParam(value = "genotypeId", required = false) Integer genotypeId) {

        List<Genotype> baseList = genotypeRepository.findAll();
        if (baseList.size() > 0) {

            if (genotypeId != null) {
                Genotype genotype = genotypeRepository.findOne(genotypeId);
                if(genotype != null){
                    baseList.retainAll((Collection<?>) genotype);
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

        return new ResponseEntity<>("NO GENOTYPES IN BASE List", HttpStatus.NO_CONTENT);


    }

}
