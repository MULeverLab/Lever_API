package application.controllers.put.schedule;

import application.entities.schedule.MethodSequence;
import application.repositories.schedule.MethodSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class PutMethodSequenceController {

    @Autowired
    private MethodSequenceRepository methodSequenceRepository;

    @PostMapping("/methodSequence")
    ResponseEntity<String> putMethodSequence (@RequestBody MethodSequence methodSequence){
        methodSequenceRepository.save(methodSequence);
        return new ResponseEntity<String>("MethodSequence is saved/updated", HttpStatus.OK);
    }

}
