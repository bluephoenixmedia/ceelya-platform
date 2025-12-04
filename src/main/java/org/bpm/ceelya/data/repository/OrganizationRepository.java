package org.bpm.ceelya.data.repository;

import org.bpm.ceelya.data.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    // We can add custom queries here later, e.g.:
    // List<Organization> findByOwnerId(String ownerId);
}