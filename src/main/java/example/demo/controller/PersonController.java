package example.demo.controller;

import example.demo.backend.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController implements CommandLineRunner {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.personService.seedData();
    }
}
