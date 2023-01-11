package example.demo.ui.view.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Collections;

@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER); // Wtih this we center the login-form
        setAlignItems(Alignment.CENTER);


        login.setAction("login"); // It will post the login to the path login, that's what we're going to setup in Spring Security
        add(
                new H1("Vaadin Library"),
                login
        );


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) { // checking whatever or not we have a parameter error.If there is we take the login screen and we'll set error to visible
        if (!beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("error", Collections.emptyList())
                .isEmpty()) {
            login.setError(true); // setting the error to visible.
        }
    }
}
