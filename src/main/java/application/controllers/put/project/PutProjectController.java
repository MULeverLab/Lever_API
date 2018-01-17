package application.controllers.put.project;

import application.entities.project.Project;
import application.repositories.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/project")
public class PutProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/project")
    ResponseEntity<String> putProject (@RequestBody Project project){
        projectRepository.save(project);
        return new ResponseEntity<String>("Project is saved/updated", HttpStatus.OK);
    }

}
