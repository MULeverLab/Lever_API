package application.controllers;

import application.entities.project.Account;
import application.repositories.project.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/auth")
public class MainController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/create/{username}/{password}")
    ResponseEntity<String> createAccount(@PathVariable String username, @PathVariable String password){
        Account account = new Account(username, password, "Ash", "Sampath", "email.com", "12345", "ADMIN", null);

        accountRepository.save(account);

        return new ResponseEntity<>(account.getFirstName() + " account saved", HttpStatus.OK);
    }
}
