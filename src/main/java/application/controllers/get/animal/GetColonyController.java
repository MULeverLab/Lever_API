package application.controllers.get.animal;

import application.entities.animal.Colony;
import application.repositories.animal.ColonyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetColonyController {

    @Autowired
    ColonyRepository colonyRepository;

    ResponseEntity<String> getColony (@RequestParam(value = "colonyId", required = false) Integer colonyId){

        Optional<List<Colony>> baseOptional = colonyRepository.findColoniesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            List<Colony> baseList = baseOptional.get();
            List<Colony> filterList;

            if (colonyId != null) {
                Colony filterOptional = colonyRepository.findColonyById(colonyId);
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

        return new ResponseEntity<>("NO ANIMALS IN BASE List", HttpStatus.NO_CONTENT);

    }
}
