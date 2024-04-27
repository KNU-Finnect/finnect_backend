package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.CellEntity;
import com.finnect.cell.adapter.out.persistence.CellId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<CellEntity, CellId> {
}
