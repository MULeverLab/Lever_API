package application.controllers.feed;

import application.entities.project.Account;
import application.entities.schedule.ScheduleEvent;
import application.repositories.project.AccountRepository;
import application.repositories.schedule.ScheduleEventRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ScheduleEventRepository scheduleEventRepository;

    @GetMapping
    ResponseEntity<String> getFeed(@AuthenticationPrincipal User user,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password){

        Account account = validateAccount(username, password);

        if(account == null){
            return new ResponseEntity<>("Failed to authenticate", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // build list of claimed events
        List<ScheduleEvent> claimedScheduleEvents = new ArrayList<>();

        Optional<List<ScheduleEvent>> optional = scheduleEventRepository.findScheduleEventsByAccount(account);
        if(optional.isPresent()){
            claimedScheduleEvents = optional.get();

            optional = scheduleEventRepository.findScheduleEventsByCompleteEquals(false);
            optional.ifPresent(claimedScheduleEvents::retainAll);
        }

        // build list of unclaimed events
        List<ScheduleEvent> unclaimedScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findScheduleEventsByAccount(null);
        if(optional.isPresent()){
            unclaimedScheduleEvents = optional.get();

            optional = scheduleEventRepository.findScheduleEventsByCompleteEquals(false);
            optional.ifPresent(unclaimedScheduleEvents::retainAll);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(claimedScheduleEvents), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Account validateAccount(String username, String password){
        Optional<Account> accountOptional = accountRepository.findAccountByUsername(username);

        if(accountOptional.isPresent()){
            Account account = accountOptional.get();

            if(account.getPassword() != null && account.getPassword().equals(password)){
                return account;
            }
        }

        return null;
    }
}
