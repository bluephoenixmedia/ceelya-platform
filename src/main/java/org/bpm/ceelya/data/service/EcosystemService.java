package org.bpm.ceelya.data.service;

import org.bpm.ceelya.data.entity.Department;
import org.bpm.ceelya.data.entity.Organization;
import org.bpm.ceelya.data.entity.Team;
import org.bpm.ceelya.data.repository.DepartmentRepository;
import org.bpm.ceelya.data.repository.OrganizationRepository;
import org.bpm.ceelya.data.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EcosystemService {

    private final DepartmentRepository departmentRepository;
    private final TeamRepository teamRepository;
    private final OrganizationRepository organizationRepository;

    public EcosystemService(DepartmentRepository departmentRepository,
                            TeamRepository teamRepository,
                            OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.teamRepository = teamRepository;
        this.organizationRepository = organizationRepository;
    }

    // --- Departments ---

    public List<Department> getDepartmentsForOrganization(UUID orgId) {
        return departmentRepository.findByOrganizationId(orgId);
    }

    @Transactional
    public Department createDepartment(UUID orgId, String name, String description) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Department dept = new Department();
        dept.setName(name);
        dept.setDescription(description);
        dept.setOrganization(org);

        return departmentRepository.save(dept);
    }

    @Transactional
    public void deleteDepartment(UUID deptId) {
        departmentRepository.deleteById(deptId);
    }

    // --- Teams ---

    public List<Team> getTeamsForDepartment(UUID deptId) {
        return teamRepository.findByDepartmentId(deptId);
    }

    @Transactional
    public Team createTeam(UUID deptId, String name, String description) {
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Team team = new Team();
        team.setName(name);
        team.setDescription(description);
        team.setDepartment(dept);

        return teamRepository.save(team);
    }

    @Transactional
    public void deleteTeam(UUID teamId) {
        teamRepository.deleteById(teamId);
    }
}