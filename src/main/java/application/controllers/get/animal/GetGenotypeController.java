package application.controllers.get.animal;

import application.entities.animal.Genotype;
import application.repositories.animal.GenotypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
