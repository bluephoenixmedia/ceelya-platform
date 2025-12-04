package org.bpm.ceelya;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme("ceelya") // Defines the visual theme name
public class CeelyaApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(CeelyaApplication.class, args);
    }

}