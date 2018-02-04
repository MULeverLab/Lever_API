package application.controllers.save.method;

import application.entities.method.VfssMethod;
import application.repositories.method.VfssMethodRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/method")
public class PutVfssMethodController {

    @Autowired
    VfssMethodRepository vfssMethodRepository;

    @PostMapping("/vfssMethod")
    ResponseEntity<String> putVfssMethod (@AuthenticationPrincipal User user,
                                          @RequestBody VfssMethod vfssMethod){
        vfssMethodRepository.save(vfssMethod);
        return new ResponseEntity<String>("VfssMethod is saved/updated", HttpStatus.OK);
    }
}
