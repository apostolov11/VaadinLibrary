package example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class) // spring security there is some clash with vaadin
public class VaadinDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaadinDemoApplication.class, args);
    }

}
