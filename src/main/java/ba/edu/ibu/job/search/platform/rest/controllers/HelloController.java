package ba.edu.ibu.job.search.platform.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/zdravo")
    public String sayHello(@RequestParam(value = "name", defaultValue = "svijete") String name){
        return "Zdravo, " + name + "!";
    }
}
