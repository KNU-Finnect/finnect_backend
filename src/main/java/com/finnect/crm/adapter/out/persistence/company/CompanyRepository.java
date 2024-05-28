package com.finnect.crm.adapter.out.persistence.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

     Optional<CompanyEntity> findByDomain(String domain);
}
