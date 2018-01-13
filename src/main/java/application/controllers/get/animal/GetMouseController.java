package application.controllers.get.animal;


import application.entities.animal.Mouse;
import application.repositories.animal.MouseRepository;
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
            List<Mouse> filterList;

            if (mouseId != null){
                Mouse filterOptional = mouseRepository.findMouseById(mouseId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);
                }
            }

            if (cageId != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByCageId(cageId);
                if(filterOptional.isPresent() && filterOptional.get().size()>0){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (coatColor != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByCoatColor(coatColor);
                if(filterOptional.isPresent() && filterOptional.get().size()>0){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (leftEarPunches != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByLeftEarPunches(leftEarPunches);
                if(filterOptional.isPresent() && filterOptional.get().size()>0){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            if (rightEarPunches != null){
                Optional<List<Mouse>> filterOptional = mouseRepository.findMICEByRightEarPunches(rightEarPunches);
                if(filterOptional.isPresent() && filterOptional.get().size()>0){
                    filterList = filterOptional.get();
                    baseList.retainAll(filterList);
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO MICE IN BASE List", HttpStatus.NO_CONTENT);

    }
}
