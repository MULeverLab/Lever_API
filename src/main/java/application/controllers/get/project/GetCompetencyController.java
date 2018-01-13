package application.controllers.get.project;

import application.entities.project.Account;
import application.entities.project.Competency;
import application.repositories.animal.AnimalRepository;
import application.repositories.animal.ColonyRepository;
import application.repositories.project.AccountRepository;
import application.repositories.project.CompetencyRepository;
import application.repositories.project.ProjectRepository;
import application.repositories.project.UserCompetencyBridgeRespository;
import application.repositories.schedule.MethodSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class GetCompetencyController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CompetencyRepository competencyRepository;

    @GetMapping("/account")
    ResponseEntity<String> getAccount(@RequestParam(value = "competencyId", required = false) Integer competencyId) {

        List<Account> baseList = (List<Account>) accountRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<Account>> filterOptional;

            if (competencyId != null) {
                Competency competency = competencyRepository.findCompetencyById(competencyId);
                if (competency != null) {
                    baseList.retainAll((Collection<?>) competency);
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO ACCOUNTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}
