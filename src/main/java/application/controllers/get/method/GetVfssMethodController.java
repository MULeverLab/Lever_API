package application.controllers.get.method;

import application.entities.method.VfssMethod;
import application.repositories.method.VfssMethodRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/method")
public class GetVfssMethodController {

    @Autowired
    VfssMethodRepository vfssMethodRepository;

    @GetMapping("/vfssmethod")
    ResponseEntity<String> getVfssMethod (@AuthenticationPrincipal User user,
                                          @RequestParam (value ="vfssId", required = false) Integer vfssId){

        List<VfssMethod> baseList = vfssMethodRepository.findAll();

        if (baseList.size()>0){
            List<VfssMethod> filterList;

            if(vfssId != null){
                VfssMethod filterOptional = vfssMethodRepository.findVfssMethodById(vfssId);
                if (filterOptional != null){
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

        return new ResponseEntity<>("NO VFSS METHODS IN BASE List", HttpStatus.NO_CONTENT);

    }

}
