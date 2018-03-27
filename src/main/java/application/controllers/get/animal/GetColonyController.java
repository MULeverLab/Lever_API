package application.controllers.get.animal;

import application.entities.animal.Colony;
import application.repositories.animal.ColonyRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetColonyController {

    private final ColonyRepository colonyRepository;

    @Autowired
    public GetColonyController(ColonyRepository colonyRepository) {
        this.colonyRepository = colonyRepository;
    }

    List<Colony> getColony (@AuthenticationPrincipal User user,
                                      @RequestParam(value = "colonyId", required = false) Integer colonyId){

        List<Colony> baseList = colonyRepository.findAll();
        if (baseList.size()>0) {

            if (colonyId != null) {
                Colony colony = colonyRepository.findOne(colonyId);
                if(colony != null){
                    baseList.retainAll((Collection<?>) colony);
                }

            }

            return baseList;
        }

        return new ArrayList<>();
    }
}
