package org.bpm.ceelya.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;

@Route("") // Maps to http://localhost:8080/
public class MainView extends VerticalLayout {

    public MainView() {
        // 1. Setup the Layout
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        // 2. Add "Nature-Inspired" Header
        H1 title = new H1("Ceelya BCID Platform");
        Paragraph subtitle = new Paragraph("Biomimetic Collective Intelligence Design - Ecosystem Online");

        // 3. Add an Interactive Element (The "Hive" entry point)
        Button button = new Button("Initialize Agentic Mesh");
        button.addClickListener(click -> {
            Notification.show("System Status: Operational. Database: Connected. Agents: Standby.");
        });

        Button createOrgButton = new Button("Go to Create Organization");
        createOrgButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        createOrgButton.addClickListener(e -> UI.getCurrent().navigate(OrganizationProfileView.class));

        add(createOrgButton);

        // 4. Add components to the view
        add(title, subtitle, button, createOrgButton);
    }
}