package application.controllers.put.method;

import application.entities.animal.Phenotype;
import application.entities.method.Method;
import application.repositories.animal.PhenotypeRepository;
import application.repositories.method.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/method")
public class PutMethodController {

    @Autowired
    MethodRepository methodRepository;

    @PostMapping("/method")
    ResponseEntity<String> putMethod (@RequestBody Method method){
        methodRepository.save(method);
        return new ResponseEntity<String>("Method is saved/updated", HttpStatus.OK);
    }

}
