package application.controllers.put.schedule;

import application.entities.schedule.ScheduleEvent;
import application.repositories.schedule.ScheduleEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class PutScheduleEvent {

    @Autowired
    private ScheduleEventRepository scheduleEventRepository;

    @PostMapping("/scheduleEvent")
    ResponseEntity<String> putScheduleEvent (@RequestBody ScheduleEvent scheduleEvent){
        scheduleEventRepository.save(scheduleEvent);
        return new ResponseEntity<String>("ScheduleEvent is saved/updated", HttpStatus.OK);
    }
}
