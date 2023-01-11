package example.demo.backend.repository;

import example.demo.backend.entity.TodoReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoReminderRepository extends JpaRepository<TodoReminder, Integer> {

}
