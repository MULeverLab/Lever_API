package application.controllers.get.method;

import application.entities.method.VfssMethods.VfssMethod;
import application.repositories.method.VfssMethodRepository;
import application.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/method")
public class GetVfssMethodController {

    private final VfssMethodRepository vfssMethodRepository;

    @Autowired
    public GetVfssMethodController(VfssMethodRepository vfssMethodRepository) {
        this.vfssMethodRepository = vfssMethodRepository;
    }

    @GetMapping("/vfssmethod")
    List<VfssMethod> getVfssMethod (@AuthenticationPrincipal User user,
                                          @RequestParam (value ="vfssId", required = false) Integer vfssId){

        List<VfssMethod> baseList = vfssMethodRepository.findAll();
        if (baseList.size()>0){

            if(vfssId != null){
                VfssMethod vfssMethod = vfssMethodRepository.findOne(vfssId);
                if (vfssMethod != null){
                   baseList.retainAll((Collection<?>) vfssMethod);
                }
            }

            return baseList;
        }

        return new ArrayList<>();
    }

}
