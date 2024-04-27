package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.CreateNewColumnUseCase;
import com.finnect.cell.application.port.out.SaveCellPort;
import com.finnect.cell.application.port.out.SaveDataColumnPort;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataColumnState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateNewColumnService implements CreateNewColumnUseCase {
    private final SaveCellPort saveCellPort;
    private final SaveDataColumnPort saveDataColumnPort;
    @Override
    public DataColumnState createNewColumn(DataColumn dataColumn) {
        log.info(dataColumn.toString());
        dataColumn = saveDataColumnPort.saveNewColumn(dataColumn);
        saveCellPort.saveNewCellByNewColumn(dataColumn);
        return dataColumn;
    }
}
