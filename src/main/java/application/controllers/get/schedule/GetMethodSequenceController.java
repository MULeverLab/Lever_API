package application.controllers.get.schedule;

import application.entities.schedule.MethodSequence;
import application.entities.schedule.MethodSequenceItem;
import application.entities.schedule.ScheduleEvent;
import application.repositories.schedule.MethodSequenceItemRepository;
import application.repositories.schedule.MethodSequenceRepository;
import application.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class GetMethodSequenceController {

    @Autowired
    private MethodSequenceRepository methodSequenceRepository;
    @Autowired
    private MethodSequenceItemRepository msiRepository;

    @GetMapping("/sequence")
    ResponseEntity<String> getMethodSequence(@AuthenticationPrincipal User user,
                                             @RequestParam(value = "sequenceId", required = false) Integer sequenceId,
                                             @RequestParam(value = "itemId", required = false) Integer itemId){

        List<MethodSequence> baseList = (List<MethodSequence>) methodSequenceRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<MethodSequence>> filterOptional;

            if (sequenceId != null) {
                Optional<MethodSequence> optional = methodSequenceRepository.findMethodSequenceById(sequenceId);
                optional.ifPresent(methodSequence -> baseList.retainAll((Collection<?>) methodSequence));
            }

            if (itemId != null) {
                Optional<MethodSequenceItem> methodSequenceItem = msiRepository.findMethodSequenceItemById(itemId);
                if (methodSequenceItem.isPresent()) {
                    filterOptional = methodSequenceRepository.findMethodSequencesByMethodSequenceItemListContains(methodSequenceItem.get());
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new ResponseEntity<>(objectMapper.writeValueAsString(baseList), HttpStatus.OK);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to convert result to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("NO PROJECTS IN BASE List", HttpStatus.NO_CONTENT);
    }
}