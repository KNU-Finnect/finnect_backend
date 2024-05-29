package com.finnect.crm.application.service.column;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.application.port.in.cell.ResetDataCellUseCase;
import com.finnect.crm.application.port.in.column.ModifyColumnUseCase;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.application.port.out.column.SaveDataColumnPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataColumnState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyColumnService implements ModifyColumnUseCase {
    private final LoadDataColumnPort loadDataColumnPort;
    private final SaveDataColumnPort saveDataColumnPort;
    private final ResetDataCellUseCase resetDataCellUseCase;
    @Override
    public DataColumnState modifyColumnInfo
            (DataColumn after) {
        DataColumn before = loadDataColumnPort.loadDataColumnByColumnId(after);
        after.modifyColumnInfo(before);
        if(!after.getColumnType().equals(before.getColumnType())){
            resetDataCellUseCase.resetColumnInfoByModifyColumn(
                    DataCell.builder()
                            .cellId(new CellId( null, after.getColumnId()) )
                            .build()
            );
        }
        return saveDataColumnPort.saveColumn(after);
    }
}
