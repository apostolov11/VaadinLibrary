package example.demo.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import example.demo.ui.reportView.ReportView;
import example.demo.ui.todoList.TodoListView;
import example.demo.ui.view.list.ListView;

@PWA(
        name = "Vaadin CRM",
        shortName = "VCRM"
)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader(); // Navbar
        createDrawer();
    }


    private void createDrawer() {
        RouterLink list = new RouterLink("List", ListView.class);
        RouterLink todoList = new RouterLink("TodoList", TodoListView.class);
        RouterLink report = new RouterLink("Report", ReportView.class);

        list.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(list, report, todoList));

    }


    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo"); // adding addClassName so we can access it from CSS

        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout); // DrawerToggle is a button that users can toggle the visibility of the navigation bar
        header.addClassName("header"); // CSS name
        header.setWidth("100%");
        header.expand(logo); // expanding the "logo" will push the logout all the way to the right
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); // the header text and the drawer toggle will be aligned with each other

        addToNavbar(header);
    }
}
