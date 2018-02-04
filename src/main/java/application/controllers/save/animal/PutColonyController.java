package application.controllers.save.animal;

import application.entities.animal.Colony;
import application.repositories.animal.ColonyRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/animal")
public class PutColonyController {


        @Autowired
        ColonyRepository colonyRepository;

        @PostMapping("/colony")
        ResponseEntity<String> putColony (@AuthenticationPrincipal User user,
                                          @RequestBody Colony colony){
            colonyRepository.save(colony);
            return new ResponseEntity<String>("Colony is saved/updated", HttpStatus.OK);
        }
    }


