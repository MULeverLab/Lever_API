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

    private final MouseRepository mouseRepository;

    @Autowired
    public PutMouseController(MouseRepository mouseRepository) {
        this.mouseRepository = mouseRepository;
    }

    @PostMapping("/mouse")
    ResponseEntity putMouse (@AuthenticationPrincipal User user,
                             @RequestBody Mouse mouse){
        mouseRepository.save(mouse);
        return new ResponseEntity(HttpStatus.OK);
    }
}
