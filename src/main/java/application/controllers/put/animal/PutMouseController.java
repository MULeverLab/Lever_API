package application.controllers.put.animal;


import application.entities.animal.Mouse;
import application.repositories.animal.MouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutMouseController {

    @Autowired
    MouseRepository mouseRepository;

    @PostMapping("/mouse")
    ResponseEntity<String> putMouse (@RequestBody Mouse mouse){
        mouseRepository.save(mouse);
        return new ResponseEntity<String>("Mouse is saved/updated", HttpStatus.OK);
    }

}
