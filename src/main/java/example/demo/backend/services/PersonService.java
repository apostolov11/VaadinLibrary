package example.demo.backend.services;

import example.demo.backend.entity.Person;

import java.io.IOException;
import java.util.List;


public interface PersonService {

    List<Person> findAll();

    List<Person> findAll(String filterText);

    long count();

    void delete(Person person);

    void save(Person person);

    void seedData() throws IOException;


}
