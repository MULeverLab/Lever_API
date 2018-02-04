package application.controllers.save.animal;


import application.entities.animal.Mouse;
import application.repositories.animal.MouseRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutMouseController {

    @Autowired
    MouseRepository mouseRepository;

    @PostMapping("/mouse")
    ResponseEntity<String> putMouse (@AuthenticationPrincipal User user,
                                     @RequestBody Mouse mouse){
        mouseRepository.save(mouse);
        return new ResponseEntity<String>("Mouse is saved/updated", HttpStatus.OK);
    }

}
