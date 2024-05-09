package com.finnect.crm.application.service.cell;

import com.finnect.crm.application.port.in.cell.CreateNewColumnUseCase;
import com.finnect.crm.application.port.out.cell.SaveCellPort;
import com.finnect.crm.application.port.out.column.SaveDataColumnPort;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;
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
