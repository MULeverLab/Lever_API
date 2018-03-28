package application.controllers.dashboard;

import application.entities.project.Account;
import application.entities.project.Competency;
import application.entities.project.UserCompetencyBridge;
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

    @GetMapping("/user")
    List<List<ScheduleEvent>> getUserFeed(@AuthenticationPrincipal User user){
        Account account = user.account;

        // build list of claimed events
        List<ScheduleEvent> claimedScheduleEvents = new ArrayList<>();

        Optional<List<ScheduleEvent>> optional = scheduleEventRepository.findByAccountAndCompleteFalseOrderByDueDate(account);
        optional.ifPresent(claimedScheduleEvents::addAll);

        // build list of unclaimed events
        List<ScheduleEvent> unclaimedScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findByAccountNullAndCompetencyNullAndCompleteFalse();
        optional.ifPresent(unclaimedScheduleEvents::addAll);

        if(account.getUserCompetencyBridgeSet() != null) {
            List<Competency> competencyList = new ArrayList<>();
            for (UserCompetencyBridge ucb : account.getUserCompetencyBridgeSet()) {
                competencyList.add(ucb.getCompetency());
            }

            optional = scheduleEventRepository.findByAccountNullAndCompetencyInAndCompleteFalseOrderByDueDate(competencyList);
            optional.ifPresent(unclaimedScheduleEvents::addAll);
        }

        // build list of complete events
        List<ScheduleEvent> completeScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findByAccountAndCompleteTrueOrderByCompletionDate(account);
        optional.ifPresent(completeScheduleEvents::addAll);


        return Arrays.asList(claimedScheduleEvents, unclaimedScheduleEvents, completeScheduleEvents);
    }

    @GetMapping("/admin")
    List<List<ScheduleEvent>> getAdminFeed(@AuthenticationPrincipal User user){
        Account account = user.account;

        if(!account.getPrivilege().equals("ADMIN")){
            return new ArrayList<>();
        }

        // build list of claimed events
        List<ScheduleEvent> claimedScheduleEvents = new ArrayList<>();

        Optional<List<ScheduleEvent>> optional = scheduleEventRepository.findByAccountNotNullAndCompleteFalseOrderByDueDate();
        optional.ifPresent(claimedScheduleEvents::addAll);

        // build list of unclaimed events
        List<ScheduleEvent> unclaimedScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findByAccountNullAndCompleteFalseOrderByDueDate();
        optional.ifPresent(unclaimedScheduleEvents::addAll);

        // build list of complete events
        List<ScheduleEvent> completeScheduleEvents = new ArrayList<>();

        optional = scheduleEventRepository.findByCompleteTrueOrderByCompletionDate();
        optional.ifPresent(completeScheduleEvents::addAll);


        return Arrays.asList(claimedScheduleEvents, unclaimedScheduleEvents, completeScheduleEvents);
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
