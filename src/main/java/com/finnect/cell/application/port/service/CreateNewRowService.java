package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.CreateNewRowUseCase;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.application.port.out.SaveNewCellUsePort;
import com.finnect.cell.domain.Column;
import com.finnect.cell.domain.DataRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewRowService implements CreateNewRowUseCase {

    private final SaveDataRowPort saveDataRowPort;
    private final SaveNewCellUsePort saveNewCellUsePort;
    @Override
    public DataRow createNewDataRow(Column column) {
        DataRow dataRow = saveDataRowPort.createNewDataRow();
        saveNewCellUsePort.saveNewCell(column, dataRow);
        return dataRow;
    }
}
