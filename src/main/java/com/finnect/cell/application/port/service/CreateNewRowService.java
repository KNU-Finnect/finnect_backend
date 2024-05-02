package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.CreateNewRowUseCase;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.application.port.out.SaveCellPort;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.DataRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewRowService implements CreateNewRowUseCase {

    private final SaveDataRowPort saveDataRowPort;
    private final SaveCellPort saveCellPort;
    @Override
    public DataRow createNewDataRow(DataColumn dataColumn) {
        DataRow dataRow = saveDataRowPort.saveNewDataRow();
        saveCellPort.saveNewCellByNewRow(dataColumn, dataRow);
        return dataRow;
    }
}
