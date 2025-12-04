package org.bpm.ceelya.data.repository;

import org.bpm.ceelya.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findByDepartmentId(UUID departmentId);
}