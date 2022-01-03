package example.demo.ui.todoList;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import example.demo.backend.entity.TodoReminder;
import example.demo.ui.MainLayout;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route(value = "todolist", layout = MainLayout.class)
public class TodoListView extends VerticalLayout {

    public TodoListView() {

        var crud = new GridCrud<>(TodoReminder.class);
        crud.getGrid().setColumns("person","text","date");
        crud.getCrudFormFactory().setVisibleProperties("person","text","date");


        add(
                new H1("TodoList here"),
                crud

        );
    }
}
