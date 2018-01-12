package application.controllers;


import application.animal.Mouse;
import application.repository.MouseRepository;
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
@RequestMapping("/get/mouse")
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

        Optional<Set<Mouse>> baseOptional = mouseRepository.findMICEByIdGreaterThan(-1);
        if (baseOptional.isPresent() && baseOptional.get().size()>0) {
            Set<Mouse> baseSet = baseOptional.get();
            Set<Mouse> filterSet;

            if (mouseId != null){
                Mouse filterOptional = mouseRepository.findMouseById(mouseId);
                if(filterOptional != null){
                    filterSet = new HashSet<Mouse>();
                    filterSet.add(filterOptional);
                    baseSet.retainAll(filterSet);
                }
            }

            if (cageId != null){
                Optional<Set<Mouse>> filterOptional = mouseRepository.findMICEByCageId(cageId);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (coatColor != null){
                Optional<Set<Mouse>> filterOptional = mouseRepository.findMICEByCoatColor(coatColor);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (leftEarPunches != null){
                Optional<Set<Mouse>> filterOptional = mouseRepository.findMICEByLeftEarPunches(leftEarPunches);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            if (rightEarPunches != null){
                Optional<Set<Mouse>> filterOptional = mouseRepository.findMICEByRightEarPunches(rightEarPunches);
                if(filterOptional.isPresent()){
                    filterSet = filterOptional.get();
                    baseSet.retainAll(filterSet);
                }
            }

            return new ResponseEntity<>(baseSet.toString(), HttpStatus.OK);

        }

        return new ResponseEntity<>("NO MICE IN BASE SET", HttpStatus.NO_CONTENT);

    }
}
