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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping("/competency")
    ResponseEntity<String> getAccount(@RequestParam(value = "competencyId", required = false) Integer competencyId) {

        List<Competency> baseList = (List<Competency>) competencyRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<Account>> filterOptional;

            if (competencyId != null) {
                Competency competency = competencyRepository.findCompetencyById(competencyId);
                if (competency != null) {
                    baseList.retainAll((Collection<?>) competency);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO ACCOUNTS IN BASE LIST", HttpStatus.NO_CONTENT);
    }
}
