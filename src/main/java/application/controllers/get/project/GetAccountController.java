package application.controllers.get.project;

import application.entities.project.Account;
import application.entities.project.Competency;
import application.entities.project.UserCompetencyBridge;
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

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/project")
public class GetAccountController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CompetencyRepository competencyRepository;
    @Autowired
    private UserCompetencyBridgeRespository userCompetencyBridgeRespository;
    @Autowired
    private ColonyRepository colonyRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MethodSequenceRepository methodSequenceRepository;

    @GetMapping("/account")
    ResponseEntity<String> getAccount(@RequestParam(value = "accountId", required = false) Integer accountId,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @RequestParam(value = "privilege", required = false) String privilege,
                                      @RequestParam(value = "competency", required = false) List<Integer> competencyIdList) {

        List<Account> baseList = accountRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<Account>> filterOptional;

            if (accountId != null) {
                Account account = accountRepository.findAccountById(accountId);
                if (account != null) {
                    baseList.retainAll((Collection<?>) account);
                }
            }

            if (username != null) {
                filterOptional = accountRepository.findAccountsByUsernameLike(username);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (firstName != null) {
                filterOptional = accountRepository.findAccountsByFirstNameLike(firstName);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (lastName != null) {
                filterOptional = accountRepository.findAccountsByLastNameLike(lastName);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (email != null) {
                filterOptional = accountRepository.findAccountsByEmailLike(email);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (phone != null) {
                filterOptional = accountRepository.findAccountsByPhoneNumber(phone);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (privilege != null) {
                filterOptional = accountRepository.findAccountsByPrivilege(privilege);
                filterOptional.ifPresent(baseList::retainAll);
            }

            if (competencyIdList != null && competencyIdList.size() > 0) {
                List<UserCompetencyBridge> userCompetencyBridgeList = new ArrayList<>();
                for(Integer id : competencyIdList){
                    Competency competency = competencyRepository.findCompetencyById(id);
                    if(competency != null){
                        Optional<List<UserCompetencyBridge>> ucbListOptional =
                                userCompetencyBridgeRespository.findUserCompetencyBridgesByCompetency(competency);
                        ucbListOptional.ifPresent(userCompetencyBridgeList::addAll);
                    }
                }

                List<Account> accountList = new ArrayList<>();
                for(UserCompetencyBridge userCompetencyBridge : userCompetencyBridgeList){
                    Optional<List<Account>> accountListOptional =
                            accountRepository.findAccountsByUserCompetencyBridgeSetContains(userCompetencyBridge);
                    accountListOptional.ifPresent(accountList::addAll);
                }

                accountList = new ArrayList<>(new HashSet<>(accountList));
                baseList.retainAll(accountList);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO ACCOUNTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}
