package application.controllers.get.animal;

import application.entities.animal.Colony;
import application.repositories.animal.ColonyRepository;
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


            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO ANIMALS IN BASE List", HttpStatus.NO_CONTENT);

    }
}
