package application.controllers.save.project;

import application.entities.project.Competency;
import application.repositories.project.CompetencyRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class PutCompetencyController {

    private final CompetencyRepository competencyRepository;

    @Autowired
    public PutCompetencyController(CompetencyRepository competencyRepository) {
        this.competencyRepository = competencyRepository;
    }

    @PostMapping("/competency")
    ResponseEntity putCompetency (@AuthenticationPrincipal User user,
                                 @RequestBody Competency competency){
        competencyRepository.save(competency);
        return new ResponseEntity(HttpStatus.OK);
    }
}
