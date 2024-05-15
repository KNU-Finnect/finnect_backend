package com.finnect.crm.adapter.out.deal;

import com.finnect.crm.adapter.out.deal.persistence.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DealRepository extends JpaRepository<DealEntity, Long > {
}
