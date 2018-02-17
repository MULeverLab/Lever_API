package application.controllers.save.schedule;

import application.entities.schedule.MethodSequence;
import application.repositories.schedule.MethodSequenceRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class PutMethodSequenceController {

    private final MethodSequenceRepository methodSequenceRepository;

    @Autowired
    public PutMethodSequenceController(MethodSequenceRepository methodSequenceRepository) {
        this.methodSequenceRepository = methodSequenceRepository;
    }

    @PostMapping("/methodSequence")
    ResponseEntity putMethodSequence (@AuthenticationPrincipal User user,
                                      @RequestBody MethodSequence methodSequence){
        methodSequenceRepository.save(methodSequence);
        return new ResponseEntity(HttpStatus.OK);
    }
}
