package org.bpm.ceelya.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bpm.ceelya.data.entity.Department;
import org.bpm.ceelya.data.entity.Organization;
import org.bpm.ceelya.data.entity.Team;
import org.bpm.ceelya.data.service.EcosystemService;
import org.bpm.ceelya.data.service.OrganizationService;

import java.util.List;

import jakarta.annotation.security.PermitAll;

@PageTitle("Ecosystem | Ceelya")
@Route(value = "ecosystem", layout = MainLayout.class)
@PermitAll
public class EcosystemView extends VerticalLayout {

    private final EcosystemService ecosystemService;
    private final OrganizationService organizationService;

    // UI Components
    private ComboBox<Organization> organizationSelect = new ComboBox<>("Select Organization");
    private Grid<Department> departmentGrid = new Grid<>(Department.class);
    private Grid<Team> teamGrid = new Grid<>(Team.class);

    // Forms
    private TextField deptNameField = new TextField("Department Name");
    private TextField teamNameField = new TextField("New Team Name");

    // State
    private Organization currentOrg;
    private Department currentDept;

    public EcosystemView(EcosystemService ecosystemService, OrganizationService organizationService) {
        this.ecosystemService = ecosystemService;
        this.organizationService = organizationService;

        setSizeFull();
        setSpacing(false);
        setPadding(false);

        // 1. Top Bar: Organization Selector
        createTopBar();

        // 2. Main Layout: Split (Departments on Left, Teams/Details on Right)
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        splitLayout.addToPrimary(createDepartmentPanel());
        splitLayout.addToSecondary(createTeamPanel());
        splitLayout.setSplitterPosition(40); // 40% width for departments

        add(splitLayout);

        // 3. Initial Data Load
        refreshOrganizations();
    }

    private void createTopBar() {
        HorizontalLayout topBar = new HorizontalLayout();
        topBar.setWidthFull();
        topBar.setPadding(true);
        topBar.setAlignItems(Alignment.BASELINE);
        topBar.getStyle().set("background-color", "var(--lumo-base-color)");
        topBar.getStyle().set("border-bottom", "1px solid var(--lumo-contrast-10pct)");

        organizationSelect.setItemLabelGenerator(Organization::getName);
        organizationSelect.setWidth("300px");
        organizationSelect.addValueChangeListener(e -> {
            currentOrg = e.getValue();
            refreshDepartments();
        });

        topBar.add(organizationSelect);
        add(topBar);
    }

    private Component createDepartmentPanel() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.getStyle().set("background-color", "#fcfcfc");

        H3 title = new H3("Departments");

        // Add Department Form (Inline)
        HorizontalLayout addForm = new HorizontalLayout();
        addForm.setWidthFull();
        deptNameField.setPlaceholder("New Department Name");
        Button addBtn = new Button(VaadinIcon.PLUS.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBtn.addClickListener(e -> createDepartment());

        addForm.add(deptNameField, addBtn);
        addForm.setFlexGrow(1, deptNameField);

        // Grid Configuration
        departmentGrid.setColumns("name", "description");
        departmentGrid.asSingleSelect().addValueChangeListener(e -> {
            currentDept = e.getValue();
            refreshTeams();
        });

        layout.add(title, addForm, departmentGrid);
        return layout;
    }

    private Component createTeamPanel() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(true);

        H4 title = new H4("Teams in Selected Department");

        // Add Team Form
        HorizontalLayout addForm = new HorizontalLayout();
        addForm.setWidthFull();
        teamNameField.setPlaceholder("New Team Name");
        Button addBtn = new Button("Add Team", VaadinIcon.PLUS.create());
        addBtn.addClickListener(e -> createTeam());

        addForm.add(teamNameField, addBtn);
        addForm.setFlexGrow(1, teamNameField);

        // Grid Configuration
        teamGrid.setColumns("name", "description");

        layout.add(title, addForm, teamGrid);
        return layout;
    }

    // --- Actions ---

    private void refreshOrganizations() {
        List<Organization> orgs = organizationService.findAll();
        organizationSelect.setItems(orgs);
        if (!orgs.isEmpty()) {
            organizationSelect.setValue(orgs.get(0));
        }
    }

    private void refreshDepartments() {
        if (currentOrg != null) {
            departmentGrid.setItems(ecosystemService.getDepartmentsForOrganization(currentOrg.getId()));
            currentDept = null; // Deselect
            refreshTeams(); // Clear teams
        } else {
            departmentGrid.setItems();
        }
    }

    private void refreshTeams() {
        if (currentDept != null) {
            teamGrid.setItems(ecosystemService.getTeamsForDepartment(currentDept.getId()));
            teamNameField.setEnabled(true);
        } else {
            teamGrid.setItems();
            teamNameField.setEnabled(false);
        }
    }

    private void createDepartment() {
        if (currentOrg == null) {
            Notification.show("Please select an organization first");
            return;
        }
        if (deptNameField.isEmpty())
            return;

        ecosystemService.createDepartment(currentOrg.getId(), deptNameField.getValue(), "");
        deptNameField.clear();
        refreshDepartments();
        Notification.show("Department created").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private void createTeam() {
        if (currentDept == null) {
            Notification.show("Please select a department first");
            return;
        }
        if (teamNameField.isEmpty())
            return;

        ecosystemService.createTeam(currentDept.getId(), teamNameField.getValue(), "");
        teamNameField.clear();
        refreshTeams();
        Notification.show("Team created").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}