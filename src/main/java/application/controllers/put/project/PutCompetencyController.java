package application.controllers.put.project;

import application.entities.project.Competency;
import application.repositories.project.CompetencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class PutCompetencyController {


    @Autowired
    private CompetencyRepository competencyRepository;

    @PostMapping("/competency")
    ResponseEntity<String> putCompetency (@RequestBody Competency competency){
        competencyRepository.save(competency);
        return new ResponseEntity<String>("Competency is saved/updated", HttpStatus.OK);
    }
}
