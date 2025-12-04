package org.bpm.ceelya.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    private String description;

    private String teamLeadId; // Placeholder for Employee ID

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // --- Getters & Setters ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTeamLeadId() { return teamLeadId; }
    public void setTeamLeadId(String teamLeadId) { this.teamLeadId = teamLeadId; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}