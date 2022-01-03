package example.demo.ui.view.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import example.demo.backend.entity.Person;
import example.demo.backend.services.PersonService;
import example.demo.ui.MainLayout;


@Route(value = "",layout = MainLayout.class)
@PageTitle("Contacts list")
public class ListView extends VerticalLayout {

    private final PersonService personService;
    private final ContactForm form;
    Grid<Person> grid = new Grid<>(Person.class);
    TextField filterText = new TextField();


    public ListView(PersonService personService) {
        this.personService = personService;
        addClassName("list-view"); // Adds a CSS class name to this component.
        setSizeFull();
        configureGrid(); // editing the grid
        searchList(); // textField search plus addContact button

        this.form = new ContactForm();
        form.addListener(ContactForm.SaveEvent.class,this::saveContact); // Hook up the save button
        form.addListener(ContactForm.DeleteEvent.class,this::deleteContact); // Hook up the delete button
        form.addListener(ContactForm.CancelEvent.class, cancelEvent -> closeEditor());

        Div content = new Div(grid, form); // Div tag so we can manipulated with CSS
        content.addClassName("content");
        content.setSizeFull();

        add(searchList(), content);
        updateList(); // filling the grid
        closeEditor(); // hiding the form if there is no contact selected
    }

    private void deleteContact(ContactForm.DeleteEvent contact) { // same steps as the saveContact method instead of saving a contact we delete it
        personService.delete(contact.getPerson());
        updateList();
        closeEditor();
    }

    private void saveContact(ContactForm.SaveEvent contact) {
        personService.save(contact.getPerson()); // save the contact in the out DB
        updateList(); // update the list to see the contact is saved
        closeEditor(); // close the ContactForm when the contact is saved
    }

    private void closeEditor() {
        form.setPerson(null);
        form.setVisible(false);
        form.removeClassName("editing");

    }

    private HorizontalLayout searchList() {  // textField search plus addContact button
        filterText.setPlaceholder("Search by name...");
        filterText.setClearButtonVisible(true); // after we write something an X button will appear to clear our writing
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        filterText.setPrefixComponent(new Icon(VaadinIcon.SEARCH));

        Button addContactButton = new Button("Add contact", click -> addContact()); // making a button for add new contact
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton); // adding
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear(); // clearing any previous selections
        editContact(new Person()); // re using editContact and passing a new Person object
    }

    private void configureGrid() {
        grid.addClassName("contact-grid"); // Adds a CSS class name to this component.
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email", "status"); // Arrangement our columns
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue())); // this row is for when we select a row from the grid and we want it to pop on the ContactFrom, and if we didn't select anything the ContactFrom to hide
    }

    private void editContact(Person contact) {
        if (contact == null) { // if we didn't select anything we don't want to see the ContactFrom
            closeEditor();
        } else { // if we select a row we want it to show on the ContactFrom and to be fill with contact values
            form.setPerson(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList() {
        grid.setItems(this.personService.findAll(filterText.getValue()));
    }


}
