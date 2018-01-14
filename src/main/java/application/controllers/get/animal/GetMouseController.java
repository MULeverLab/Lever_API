package application.controllers.get.animal;


import application.entities.animal.Mouse;
import application.repositories.animal.MouseRepository;
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
import java.util.Collection;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/animal")
public class GetMouseController {

    @Autowired
    MouseRepository mouseRepository;

    @RequestMapping("/mouse")
    ResponseEntity<String> getMouse(@RequestParam(value = "mouseId", required = false) Integer mouseId,
                                    @RequestParam(value = "cageId", required = false) Integer cageId,
                                    @RequestParam(value = "coatColor", required = false) Integer coatColor,
                                    @RequestParam(value = "leftEarPunches", required = false) Integer leftEarPunches,
                                    @RequestParam(value = "rightEarPunches", required = false) Integer rightEarPunches
                                    )
    {

        Optional<List<Mouse>> baseOptional = mouseRepository.findMICEByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            List<Mouse> baseList = baseOptional.get();

            if (mouseId != null){
                Mouse filterOptional = mouseRepository.findMouseById(mouseId);
                if(filterOptional != null){
                    baseList.retainAll((Collection<?>) filterOptional);
                }
            }

            if (cageId != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByCageId(cageId);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (coatColor != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByCoatColor(coatColor);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (leftEarPunches != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByLeftEarPunches(leftEarPunches);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (rightEarPunches != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByRightEarPunches(rightEarPunches);
                filterOptional.ifPresent(baseList::retainAll);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO MICE IN BASE List", HttpStatus.NO_CONTENT);

    }
}