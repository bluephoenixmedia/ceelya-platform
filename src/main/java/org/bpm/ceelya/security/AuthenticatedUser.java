package org.bpm.ceelya.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.bpm.ceelya.data.entity.Employee;
import org.bpm.ceelya.data.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<Employee> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> employeeRepository.findByUsername(userDetails.getUsername()).orElse(null));
    }

    public void logout() {
        authenticationContext.logout();
    }
}
