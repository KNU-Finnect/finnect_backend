package com.finnect.deal.adapter.out;

import com.finnect.deal.adapter.out.persistence.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DealRepository extends JpaRepository<DealEntity, Long > {
}
