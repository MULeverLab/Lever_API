package application.controllers.get.schedule;

import application.entities.schedule.MethodSequence;
import application.entities.schedule.MethodSequenceItem;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/schedule")
public class GetMethodSequenceController {

    private final MethodSequenceRepository methodSequenceRepository;
    private final MethodSequenceItemRepository msiRepository;

    @Autowired
    public GetMethodSequenceController(MethodSequenceRepository methodSequenceRepository, MethodSequenceItemRepository msiRepository) {
        this.methodSequenceRepository = methodSequenceRepository;
        this.msiRepository = msiRepository;
    }

    @GetMapping("/sequence")
    List<MethodSequence> getMethodSequence(@AuthenticationPrincipal User user,
                                             @RequestParam(value = "sequenceId", required = false) Integer sequenceId,
                                             @RequestParam(value = "itemId", required = false) Integer itemId){

        List<MethodSequence> baseList = methodSequenceRepository.findAll();
        if (baseList.size() > 0) {
            Optional<List<MethodSequence>> filterOptional;

            if (sequenceId != null) {
                MethodSequence methodSequence = methodSequenceRepository.findOne(sequenceId);

                if(methodSequence != null){
                    baseList.retainAll((Collection<?>) methodSequence);
                }
            }

            if (itemId != null) {
                MethodSequenceItem methodSequenceItem = msiRepository.findOne(itemId);

                if (methodSequenceItem != null) {
                    filterOptional = methodSequenceRepository.findByMethodSequenceItemListContains(methodSequenceItem);
                    filterOptional.ifPresent(baseList::retainAll);
                }
            }

            return baseList;
        }

        return new ArrayList<>();
    }
}