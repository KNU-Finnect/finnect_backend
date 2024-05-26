package com.finnect.crm.adapter.out.persistence.person;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
