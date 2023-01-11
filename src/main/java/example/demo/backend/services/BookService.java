package example.demo.backend.services;

import example.demo.backend.entity.Book;
import example.demo.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
public class BookService implements CrudListener<Book> {

    private final BookRepository bookRepo;

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public Collection<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book add(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }
}
