package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.domain.person.PersonState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonState> findAllByCompanyIdOrderByPersonName(Long companyId);
}
