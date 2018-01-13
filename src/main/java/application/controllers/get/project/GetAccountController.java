package application.controllers.get.project;

import application.entities.project.Account;
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
import java.util.Optional;
import java.util.List;

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
                                      @RequestParam(value = "privilege", required = false) String privilege) {

        List<Account> baseList = (List<Account>) accountRepository.findAll();
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
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if (firstName != null) {
                filterOptional = accountRepository.findAccountsByFirstNameLike(firstName);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if (lastName != null) {
                filterOptional = accountRepository.findAccountsByLastNameLike(lastName);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if (email != null) {
                filterOptional = accountRepository.findAccountsByEmailLike(email);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if (phone != null) {
                filterOptional = accountRepository.findAccountsByPhoneNumber(phone);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            if (privilege != null) {
                filterOptional = accountRepository.findAccountsByPrivilege(privilege);
                if (filterOptional.isPresent() && filterOptional.get().size() > 0) {
                    baseList.retainAll(filterOptional.get());
                }
            }

            return new ResponseEntity<>(baseList.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("NO ACCOUNTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}
