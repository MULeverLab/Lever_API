package application.controllers.save.method;

import application.entities.method.Method;
import application.repositories.method.MethodRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/method")
public class PutMethodController {

    private final MethodRepository methodRepository;

    @Autowired
    public PutMethodController(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    @PostMapping("/method")
    ResponseEntity putMethod (@AuthenticationPrincipal User user,
                              @RequestBody Method method){
        methodRepository.save(method);
        return new ResponseEntity(HttpStatus.OK);
    }
}
