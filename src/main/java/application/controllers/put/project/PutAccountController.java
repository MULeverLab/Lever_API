package application.controllers.put.project;

import application.entities.project.Account;
import application.repositories.project.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/project")
public class PutAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account")
    ResponseEntity<String> putAccount (@RequestBody Account account){
        accountRepository.save(account);
        return new ResponseEntity<String>("Account is saved/updated", HttpStatus.OK);
    }
}
