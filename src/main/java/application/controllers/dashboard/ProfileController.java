package application.controllers.dashboard;

import application.entities.project.Account;
import application.repositories.project.AccountRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profile")
public class ProfileController {

    @Value("${storage.path.profile}")
    private String picturePath;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/me")
    Account getMe(@AuthenticationPrincipal User user){
        return accountRepository.findOne(user.account.getId());
    }

    @PostMapping("/me/picture")
    ResponseEntity updatePicture(@AuthenticationPrincipal User user, @RequestParam MultipartFile picture){
        String fileName = UUID.randomUUID().toString().substring(0, 9) + picture.getOriginalFilename();

        try {
            Files.write(Paths.get(picturePath + fileName), picture.getBytes());
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Account account = user.account;
        account.setPictureName(fileName);
        accountRepository.save(account);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{username}")
    Account getAccount(@PathVariable String username){
        return accountRepository.findByUsername(username).orElse(null);
    }
}
