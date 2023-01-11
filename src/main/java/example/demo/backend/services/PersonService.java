package example.demo.backend.services;

import example.demo.backend.entity.Person;
import example.demo.backend.entity.enums.Status;
import example.demo.backend.repository.PersonRepository;
import example.demo.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PersonService {

    private static final String NAME_FILE_PATH = "src/main/resources/files/names.txt";

    private final PersonRepository personRepository;
    private final FileUtil fileUtil;

    public PersonService(PersonRepository personRepository, FileUtil fileUtil) {
        this.personRepository = personRepository;
        this.fileUtil = fileUtil;
    }



    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public List<Person> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return this.personRepository.findAll();
        } else {
            return this.personRepository.search(filterText);
        }
    }


    public long count() {
        return this.personRepository.count();
    }


    public void delete(Person person) {
        this.personRepository.delete(person);
    }


    public void save(Person person) {
        if (person == null) {
            throw new NullPointerException("There is no such person.");
        }
        this.personRepository.save(person);
    }


    public void seedData() throws IOException {
        Random rnd = new Random(); // we use random to set random status

        if (this.personRepository.count() != 0) {
            return;
        }

        String[] persons = this.fileUtil.fileContent(NAME_FILE_PATH);

        for (String s : persons) {
            Person person = new Person();
            String[] tokens = s.split("\\s+"); // we split the array so we can set the names correctly
            person.setFirstName(tokens[0]);
            person.setLastName(tokens[1]);
            person.setEmail(tokens[2]);
            person.setStatus(Status.values()[rnd.nextInt(Status.values().length)]); // for email we  take status values with random we get random Status, for length we take status length

            this.personRepository.saveAndFlush(person);
        }
    }

}
