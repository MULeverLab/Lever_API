package application.controllers.get.method;


import application.entities.method.Method;
import application.entities.method.VfssBolusMethod;
import application.entities.method.VfssPositionOneMethod;
import application.entities.method.VfssPositionTwoMethod;
import application.repositories.method.MethodRepository;
import application.repositories.method.VfssBolusMethodRepository;
import application.repositories.method.VfssPositionOneMethodRepository;
import application.repositories.method.VfssPositionTwoMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/method")
public class GetMethodController {

    @Autowired
    MethodRepository methodRepository;

    @GetMapping("/method")
    ResponseEntity<String> getMethod (@RequestParam(value = "rowId", required = false) Integer rowId,
                                      @RequestParam(value = "dateBefore", required = false) Long dateBefore,
                                      @RequestParam(value = "dateAfter", required = false) Long dateAfter,
                                      @RequestParam(value ="methodType", required = false) Integer methodType,
                                      @RequestParam(value ="methodId", required = false) Integer methodId
                                      ){
        List<Method> baseList = methodRepository.findAll();

        if(baseList.size()>0){
            List<Method> filterList;

            if(rowId != null){
                Method filterOptional = methodRepository.findMethodById(rowId);
                if(filterOptional != null){
                    filterList = new ArrayList<>();
                    filterList.add(filterOptional);
                    baseList.retainAll(filterList);

                }
            }

            if(dateBefore != null){
                Optional<List<Method>> filterOptional = methodRepository.findMethodsByDateLessThan(dateBefore);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(dateAfter != null){
                Optional<List<Method>> filterOptional = methodRepository.findMethodsByDateGreaterThan(dateAfter);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(methodId != null){
                Optional<List<Method>> filterOptional = methodRepository.findMethodsByMethodId(methodId);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if(methodType != null){
                Optional<List<Method>> filterOptional = methodRepository.findMethodsByMethodType(methodType);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);

        }


        return new ResponseEntity<>("NO METHODS IN BASE List", HttpStatus.NO_CONTENT);
    }

}
