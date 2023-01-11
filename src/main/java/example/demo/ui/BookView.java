package example.demo.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import example.demo.backend.entity.Book;
import example.demo.backend.services.BookService;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "book", layout = MainLayout.class)
public class BookView extends VerticalLayout {


    public BookView(BookService bookService) {

        GridCrud<Book> crud = new GridCrud<>(Book.class, bookService);

        crud.getGrid().setColumns("bookName","authorName","genre","copies");
        crud.getCrudFormFactory().setVisibleProperties("bookName","authorName","genre","copies");


        add(
                new H1("Add book"),
                crud);
    }
}
