package example.demo.ui.reportView;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import example.demo.backend.entity.Person;

import example.demo.backend.services.PersonService;
import example.demo.ui.MainLayout;
import org.vaadin.reports.PrintPreviewReport;


@Route(value = "report", layout = MainLayout.class)
public class ReportView extends VerticalLayout {

    public ReportView(PersonService service) { // DI personService
        setSizeFull();
        PrintPreviewReport<Person> report = new PrintPreviewReport<>(Person.class, "firstName", "lastName", "email", "status"); // we get the columns name from the Person.class
        report.setItems(service.findAll()); // report.setItems to get the items and from service.findAll to fill the report with all the database
        report.getReportBuilder().setTitle("Persons");

        StreamResource pdf = report.getStreamResource("person.pdf", service::findAll, PrintPreviewReport.Format.PDF);
        StreamResource csv  = report.getStreamResource("person.cvs", service::findAll, PrintPreviewReport.Format.CSV);
        StreamResource docx  = report.getStreamResource("person.docx", service::findAll, PrintPreviewReport.Format.DOCX);

        add(
                new HorizontalLayout(
                        new Anchor(pdf,"PDF"),
                        new Anchor(csv,"CSV"),
                        new Anchor(docx,"DOCX")
                ),
                report
        );
    }
}
