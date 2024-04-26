package com.finnect.cell.adapter.out;

import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.domain.DataRow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class CellPersistenceAdapter implements SaveDataRowPort {
    private final DataRowRepository dataRowRepository;
    @Override
    @Transactional
    public DataRow createNewDataRow() {
        DataRowEntity dataRowEntity = new DataRowEntity(null);
        dataRowRepository.save(dataRowEntity);
        log.info(dataRowEntity.getDataRowId().toString());

        return dataRowEntity.toDomain();
    }
}
