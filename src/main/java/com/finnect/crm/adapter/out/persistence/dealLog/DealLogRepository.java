package com.finnect.crm.adapter.out.persistence.dealLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealLogRepository extends JpaRepository<DealLogEntity, Long> {
}
