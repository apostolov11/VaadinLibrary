package example.demo.backend.repository;

import example.demo.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b " +
            "where lower(b.bookName) like lower(concat('%', :filterText, '%')) " +
            "or lower(b.authorName) like  lower(concat('%', :filterText, '%'))")
    List<Book> filter(@Param("filterText")String filterText);

}
