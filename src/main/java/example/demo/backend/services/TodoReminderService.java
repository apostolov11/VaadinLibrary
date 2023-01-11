package example.demo.backend.services;

import example.demo.backend.entity.TodoReminder;
import example.demo.backend.repository.TodoReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.Collection;


@Service
public class TodoReminderService implements CrudListener<TodoReminder> {

    private final TodoReminderRepository repository;

    @Autowired
    public TodoReminderService(TodoReminderRepository repository) {
        this.repository = repository;
    }


    @Override
    public Collection<TodoReminder> findAll() {
        return repository.findAll();
    }

    @Override
    public TodoReminder add(TodoReminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public TodoReminder update(TodoReminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public void delete(TodoReminder reminder) {
        repository.delete(reminder);
    }
}
