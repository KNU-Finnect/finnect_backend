package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import javax.swing.JPanel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRowRepository extends JpaRepository<DataRowEntity, Long> {
}
