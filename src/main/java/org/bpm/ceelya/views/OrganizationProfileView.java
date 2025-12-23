package org.bpm.ceelya.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bpm.ceelya.data.service.OrganizationService;

import jakarta.annotation.security.PermitAll;

@PageTitle("Create Organization | Ceelya")
@Route(value = "create-organization", layout = MainLayout.class)
@PermitAll
public class OrganizationProfileView extends Composite<VerticalLayout> {

    private final OrganizationService organizationService;

    public OrganizationProfileView(OrganizationService organizationService) {
        this.organizationService = organizationService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        // 1. Create the Card Container
        VerticalLayout card = new VerticalLayout();
        card.setWidth("100%");
        card.setMaxWidth("600px");
        card.setPadding(true);
        card.setSpacing(true);
        // Simple card styling using built-in Lumo utility classes or inline styles
        card.getStyle().set("box-shadow", "0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)");
        card.getStyle().set("border-radius", "0.5rem");
        card.getStyle().set("background-color", "white");
        card.getStyle().set("border", "1px solid #e5e7eb"); // Light gray border

        // 2. Header Section
        H2 title = new H2("Create Organization");
        title.getStyle().set("margin-top", "0");

        Paragraph subtitle = new Paragraph("Start your biomimetic AI journey by setting up your organization.");
        subtitle.getStyle().set("color", "var(--lumo-secondary-text-color)");

        // 3. Form Fields
        FormLayout formLayout = new FormLayout();

        TextField nameField = new TextField("Organization Name");
        nameField.setPlaceholder("Enter your company name");
        nameField.setRequiredIndicatorVisible(true);

        TextArea descField = new TextArea("Description (Optional)");
        descField.setPlaceholder("Brief description of your organization");
        descField.setMinHeight("100px");

        formLayout.add(nameField, descField);

        // 4. Buttons
        Button saveButton = new Button("Create Organization");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.getStyle().set("background-color", "var(--ceelya-primary)"); // Use our custom forest green

        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(e -> nameField.clear()); // Simple clear for now

        // 5. Logic: Handle Save
        saveButton.addClickListener(e -> {
            if (nameField.isEmpty()) {
                Notification.show("Name is required").addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            try {
                // Hardcoded ownerId for now until we implement Auth
                organizationService.createOrganization(nameField.getValue(), descField.getValue(), "admin-user");

                Notification success = Notification.show("Organization created successfully!");
                success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                // Clear form after success
                nameField.clear();
                descField.clear();

            } catch (Exception ex) {
                Notification.show("Error: " + ex.getMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        // 6. Assemble
        VerticalLayout buttonLayout = new VerticalLayout(saveButton, cancelButton);
        buttonLayout.setPadding(false);

        card.add(title, subtitle, formLayout, buttonLayout);
        getContent().add(card);
    }
}