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

        String fileName = null;
        if(picture != null){
            fileName = UUID.randomUUID().toString().substring(0, 9) + picture.getOriginalFilename();

            try {
                Files.write(Paths.get(picturePath + fileName), picture.getBytes());
                return new ResponseEntity(HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        account.setPictureName(fileName);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return new ResponseEntity(HttpStatus.OK);
    }
}
