package example.demo.ui.view.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import example.demo.backend.entity.Person;
import example.demo.backend.entity.enums.Status;


public class ContactForm extends FormLayout {

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField email = new TextField("Email");
    ComboBox<Status> status = new ComboBox<>("Status");


    Button save = new Button("Save", new Icon(VaadinIcon.INSERT));
    Button delete = new Button("Delete", new Icon(VaadinIcon.TRASH));
    Button cancel = new Button("Cancel");

    Binder<Person> binder = new BeanValidationBinder<>(Person.class);


    public ContactForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this); // "this"  is for this layout that we are in.
        status.setItems(Status.values());

        add(
                firstName, lastName, email, status,
                createButtonsLayout()
        );
    }

    public void setPerson(Person person) {
        binder.setBean(person);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE); // addClickShortcut is a keyboard shortcut if we have a form open and click ENTER button it will save it and if we click ESC button it will close it

        save.addClickListener(click -> validateAndSave()); // before we save the data in the DB we need to confirm if the data is correct
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(click -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid())); // disabling the Save button whenever the field is not in valid state

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this,binder.getBean()));
        }
    }

    //Events

    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Person person;

        public ContactFormEvent(ContactForm source, Person person) {
            super(source, false);
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        public SaveEvent(ContactForm source, Person person) {
            super(source, person);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        public DeleteEvent(ContactForm source, Person person) {
            super(source, person);
        }
    }

    public static class CancelEvent extends ContactFormEvent {
        public CancelEvent(ContactForm source) {
            super(source,null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
