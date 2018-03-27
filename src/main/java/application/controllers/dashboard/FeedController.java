package application.controllers.dashboard;

import application.entities.project.Account;
import application.entities.schedule.ScheduleEvent;
import application.repositories.project.AccountRepository;
import application.repositories.schedule.ScheduleEventRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    List<List<ScheduleEvent>> getFeed(@AuthenticationPrincipal User user){


        Account account = user.account;

        if(account == null){
            return new ArrayList<>();
        }

        // build list of claimed events
        List<ScheduleEvent> claimedScheduleEvents = new ArrayList<>();

        Optional<List<ScheduleEvent>> optional = scheduleEventRepository.findByAccount(account);
        if(optional.isPresent()){
            claimedScheduleEvents = optional.get();

            optional = scheduleEventRepository.findByCompleteEquals(false);
            optional.ifPresent(claimedScheduleEvents::retainAll);
        }

        // build list of unclaimed events
        List<ScheduleEvent> unclaimedScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findByAccount(null);
        if(optional.isPresent()){
            unclaimedScheduleEvents = optional.get();

            optional = scheduleEventRepository.findByCompleteEquals(false);
            optional.ifPresent(unclaimedScheduleEvents::retainAll);
        }

        return Arrays.asList(claimedScheduleEvents, unclaimedScheduleEvents);
    }

    private Account validateAccount(String username, String password){
        Optional<Account> accountOptional = accountRepository.findByUsername(username);

        if(accountOptional.isPresent()){
            Account account = accountOptional.get();

            if(account.getPassword() != null && account.getPassword().equals(password)){
                return account;
            }
        }

        return null;
    }
}
