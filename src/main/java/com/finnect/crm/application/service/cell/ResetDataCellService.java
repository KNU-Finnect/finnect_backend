package com.finnect.crm.application.service.cell;

import com.finnect.crm.application.port.in.cell.ResetDataCellUseCase;
import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.application.port.out.cell.SaveCellPort;
import com.finnect.crm.domain.cell.DataCell;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetDataCellService implements ResetDataCellUseCase {
    private final LoadDataCellPort loadDataCellPort;
    private final SaveCellPort saveCellPort;
    @Override
    public void resetColumnInfoByModifyColumn(DataCell dataCell) {
        List<DataCell> dataCells = loadDataCellPort.loadDataCellsByColumnId(dataCell);
        dataCells.forEach(DataCell::resetValue);
        saveCellPort.saveDataCells(new ArrayList<>(dataCells));
    }
}
