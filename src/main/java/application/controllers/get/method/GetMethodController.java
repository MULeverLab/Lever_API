package application.controllers.get.method;


import application.entities.method.Method;
import application.repositories.method.MethodRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/method")
public class GetMethodController {

    private final MethodRepository methodRepository;

    @Autowired
    public GetMethodController(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    @GetMapping("/method")
    ResponseEntity<String> getMethod (@AuthenticationPrincipal User user,
                                      @RequestParam(value = "rowId", required = false) Integer rowId,
                                      @RequestParam(value = "dateBefore", required = false) Long dateBefore,
                                      @RequestParam(value = "dateAfter", required = false) Long dateAfter,
                                      @RequestParam(value ="methodType", required = false) Integer methodType,
                                      @RequestParam(value ="methodId", required = false) Integer methodId
                                      ){

        List<Method> baseList = methodRepository.findAll();
        if(baseList.size()>0){
            List<Method> filterList;

            if(rowId != null){
                Method method = methodRepository.findOne(rowId);
                if(method != null){
                    baseList.retainAll((Collection<?>) method);
                }
            }

            if(dateBefore != null){
                Optional<List<Method>> filterOptional = methodRepository.findByDateLessThan(dateBefore);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(dateAfter != null){
                Optional<List<Method>> filterOptional = methodRepository.findByDateGreaterThan(dateAfter);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(methodId != null){
                Optional<List<Method>> filterOptional = methodRepository.findByMethodId(methodId);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if(methodType != null){
                Optional<List<Method>> filterOptional = methodRepository.findByMethodType(methodType);
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


        return new ResponseEntity<>("NO METHODS IN BASE List", HttpStatus.NO_CONTENT);
    }

}
