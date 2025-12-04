package org.bpm.ceelya.data.service;

import org.bpm.ceelya.data.entity.Organization;
import org.bpm.ceelya.data.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> findById(UUID id) {
        return organizationRepository.findById(id);
    }

    @Transactional
    public Organization createOrganization(String name, String description, String ownerId) {
        Organization org = new Organization();
        org.setName(name);
        org.setDescription(description);
        // In the future, this will come from the authenticated User session
        org.setOwnerId(ownerId);
        return organizationRepository.save(org);
    }

    @Transactional
    public void delete(UUID id) {
        organizationRepository.deleteById(id);
    }

    @Transactional
    public Organization update(Organization organization) {
        return organizationRepository.save(organization);
    }
}