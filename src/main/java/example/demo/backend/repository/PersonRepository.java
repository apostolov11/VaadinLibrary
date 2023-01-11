package example.demo.backend.repository;

import example.demo.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


    @Query("select p from Person p " +
            "where lower(p.firstName) like lower(concat('%', :filterText, '%')) " +
            "or lower(p.lastName) like  lower(concat('%', :filterText, '%'))")
    List<Person> search(@Param("filterText")String filterText);

}
