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

    @org.springframework.context.annotation.Bean
    public org.springframework.boot.CommandLineRunner loadData(
            org.bpm.ceelya.data.repository.EmployeeRepository repository,
            org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                org.bpm.ceelya.data.entity.Employee admin = new org.bpm.ceelya.data.entity.Employee();
                admin.setUsername("admin");
                admin.setHashedPassword(passwordEncoder.encode("password"));
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setRole(org.bpm.ceelya.data.entity.Role.ADMIN);
                repository.save(admin);
            }
        };
    }
}