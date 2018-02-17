package application.controllers.save.project;

import application.entities.project.Project;
import application.repositories.project.ProjectRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/project")
public class PutProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    public PutProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("/project")
    ResponseEntity putProject (@AuthenticationPrincipal User user,
                               @RequestBody Project project){
        projectRepository.save(project);
        return new ResponseEntity(HttpStatus.OK);
    }
}
