package com.finnect.crm.adapter.out.persistence.company;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

     Optional<CompanyEntity> findByWorkspaceIdAndDomain(Long workspaceId, String domain);

     List<CompanyEntity> findCompanyEntitiesByWorkspaceId(Long workspaceId);
}
