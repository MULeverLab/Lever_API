package application.controllers.save.project;

import application.entities.project.Account;
import application.repositories.project.AccountRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/save/project")
public class PutAccountController {

    @Value("${storage.path.profile}")
    private String picturePath;

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PutAccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/account")
    public ResponseEntity putAccount(@AuthenticationPrincipal User user,
                                             @RequestBody Account account, @RequestParam(required = false) MultipartFile picture){
        if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            return new ResponseEntity<>("Username aleady exists", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(picture != null){
            String fileName = UUID.randomUUID().toString().substring(0, 9) + picture.getOriginalFilename();

            try {
                Files.write(Paths.get(picturePath + fileName), picture.getBytes());
                account.setPictureName(fileName);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/account/{username}/picture")
    public ResponseEntity<String> putAccountPicture(@AuthenticationPrincipal User user, @PathVariable String username,
                                                    @RequestParam MultipartFile picture){

        Optional<Account> account = accountRepository.findByUsername(username);
        if(account.isPresent()) {
            String fileName = UUID.randomUUID().toString().substring(0, 9) + picture.getOriginalFilename();

            try {
                Files.write(Paths.get(picturePath + fileName), picture.getBytes());
                account.get().setPictureName(fileName);

                return new ResponseEntity<String>(HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else{
            return new ResponseEntity<>("Username does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
