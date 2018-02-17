package application.controllers.get.project;

import application.entities.project.Account;
import application.entities.project.Competency;
import application.repositories.project.AccountRepository;
import application.repositories.project.CompetencyRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class GetCompetencyController {

    private final CompetencyRepository competencyRepository;

    @Autowired
    public GetCompetencyController(CompetencyRepository competencyRepository) {
        this.competencyRepository = competencyRepository;
    }

    @GetMapping("/competency")
    ResponseEntity<String> getAccount(@AuthenticationPrincipal User user,
                                      @RequestParam(value = "competencyId", required = false) Integer competencyId) {

        List<Competency> baseList = competencyRepository.findAll();
        if (baseList.size() > 0) {

            if (competencyId != null) {
                Competency competency = competencyRepository.findOne(competencyId);

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
