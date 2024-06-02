package com.finnect.crm.application.service.column;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.application.port.in.cell.ModifyDataCellUseCase;
import com.finnect.crm.application.port.in.column.ModifyColumnUseCase;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.application.port.out.column.SaveDataColumnPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyColumnService implements ModifyColumnUseCase {
    private final LoadDataColumnPort loadDataColumnPort;
    private final SaveDataColumnPort saveDataColumnPort;
    private final ModifyDataCellUseCase modifyDataCellUseCase;
    @Override
    public DataColumnState modifyColumnInfo
            (DataColumn after) {
        DataColumn before = loadDataColumnPort.loadDataColumnByColumnId(after);
        after.modifyColumnInfo(before);
        if(!after.getColumnType().equals(before.getColumnType())){
            modifyDataCellUseCase.resetCellInfoByModifyColumn(
                    DataCell.builder()
                            .cellId(new CellId( null, after.getColumnId()) )
                            .build()
            );
        }
        return saveDataColumnPort.saveColumn(after);
    }
}
