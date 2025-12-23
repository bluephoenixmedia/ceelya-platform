package org.bpm.ceelya.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import org.bpm.ceelya.security.AuthenticatedUser;
import org.bpm.ceelya.data.entity.Employee;

import java.util.Optional;

public class MainLayout extends AppLayout {

    private final AuthenticatedUser authenticatedUser;

    public MainLayout(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Ceelya");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink homeLink = new RouterLink("Home", MainView.class);
        RouterLink createOrgLink = new RouterLink("Create Organization", OrganizationProfileView.class);
        RouterLink ecosystemLink = new RouterLink("Ecosystem", EcosystemView.class);

        VerticalLayout layout = new VerticalLayout(homeLink, createOrgLink, ecosystemLink);

        Optional<Employee> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            Button logout = new Button("Logout", click -> authenticatedUser.logout());
            logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            layout.add(logout);

            layout.add("User: " + maybeUser.get().getUsername());
        }

        addToDrawer(layout);
    }
}
