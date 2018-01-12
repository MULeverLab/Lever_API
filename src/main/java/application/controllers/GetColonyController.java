package application.controllers;

import application.animal.Colony;
import application.repository.ColonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/colony")
public class GetColonyController {

    @Autowired
    ColonyRepository colonyRepository;

    ResponseEntity<String> getColony (@RequestParam(value = "colonyId", required = false) Integer colonyId){

        Optional<Set<Colony>> baseOptional = colonyRepository.findColoniesByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            Set<Colony> baseSet = baseOptional.get();
            Set<Colony> filterSet;

            if (colonyId != null) {
                Colony filterOptional = colonyRepository.findColonyById(colonyId);
                if(filterOptional != null){
                    filterSet = new HashSet<Colony>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }

            }


            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO ANIMALS IN BASE SET", HttpStatus.NO_CONTENT);

    }
}
