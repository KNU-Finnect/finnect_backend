package com.finnect.crm.adapter.out.persistence.deal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DealRepository extends JpaRepository<DealEntity, Long > {
}
