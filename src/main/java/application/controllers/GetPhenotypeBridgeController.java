package application.controllers;


import application.repository.PhenotypeBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/get/phenotypebridge")
public class GetPhenotypeBridgeController {

    @Autowired
    PhenotypeBridgeRepository phenotypeBridgeRepository;


    @RequestMapping("/phenotypebridge")
    ResponseEntity<String> getPhenotypeBridgeRepository(@RequestParam(value = "phenotypeId", required = false)
                                                                Integer phenotypeId){


        return new ResponseEntity<>("test", HttpStatus.OK);

    }
}
