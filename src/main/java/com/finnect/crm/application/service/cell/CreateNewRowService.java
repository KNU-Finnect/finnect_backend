package com.finnect.crm.application.service.cell;

import com.finnect.crm.application.port.in.cell.CreateNewRowUseCase;
import com.finnect.crm.application.port.out.cell.SaveDataRowPort;
import com.finnect.crm.application.port.out.cell.SaveCellPort;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.DataRow;
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
