package application.controllers.save.project;

import application.entities.project.Account;
import application.repositories.project.AccountRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/project")
public class PutAccountController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PutAccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/account")
//    ResponseEntity<String> putAccount (@RequestBody Account account){
//        accountRepository.save(account);
//        return new ResponseEntity<String>("Account is saved/updated", HttpStatus.OK);
//    }

    @PostMapping(path = "/account")
    public ResponseEntity putAccount(@AuthenticationPrincipal User user,
                                     @RequestBody Account account){
        if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            return new ResponseEntity<>("Username aleady exists", HttpStatus.METHOD_NOT_ALLOWED);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return new ResponseEntity(HttpStatus.OK);
    }
}
