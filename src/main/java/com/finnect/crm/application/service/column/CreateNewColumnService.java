package com.finnect.crm.application.service.column;

import com.finnect.crm.application.port.in.column.CreateNewColumnUseCase;
import com.finnect.crm.application.port.out.cell.SaveCellPort;
import com.finnect.crm.application.port.out.column.SaveDataColumnPort;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.DataType;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.application.port.in.ModifyViewUseCase;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateNewColumnService implements CreateNewColumnUseCase {
    private final SaveCellPort saveCellPort;
    private final SaveDataColumnPort saveDataColumnPort;
    private final ModifyViewUseCase modifyViewUseCase;
    @Override
    public DataColumnState createNewColumn(DataColumn dataColumn) {
        log.info(dataColumn.toString());
        dataColumn = saveDataColumnPort.saveColumn(dataColumn);

        modifyViewUseCase.addViewColumn(dataColumn);
        saveCellPort.saveNewCellByNewColumn(dataColumn);
        return dataColumn;
    }

    @Override
    public void createDefaultColumn(Long workspaceId) {
        List<DataColumn> defaultDealColumns = getDefaultColumns(workspaceId);
        defaultDealColumns = saveDataColumnPort.saveColumns(new ArrayList<>(defaultDealColumns));
        for(DataColumn dataColumn: defaultDealColumns){
            saveCellPort.saveNewCellByNewColumn(dataColumn);
        }
        modifyViewUseCase.addViewColumns(new ArrayList<>(defaultDealColumns));
    }
    private List<DataColumn> getDefaultColumns(Long workspaceId){
        List<DataColumn> defaultColumns = generateDefaultDealColumns(workspaceId);
        defaultColumns.addAll(generateDefaultCompanyColumns(workspaceId));
        return defaultColumns;
    }
    private List<DataColumn> generateDefaultDealColumns(Long workspaceId){
        List<DataColumn> defaultColumns = new ArrayList<>();
        defaultColumns.add(DataColumn.builder()
                .columnType(ColumnType.DATE)
                .workspaceId(workspaceId)
                .columnName("생성일")
                .columnIndex(1.0)
                .dType(DataType.DEAL)
                .isHided(false)
                .build()
        );
        defaultColumns.add(DataColumn.builder()
                .columnType(ColumnType.CURRENCY)
                .workspaceId(workspaceId)
                .columnName("거래액")
                .columnIndex(5.0)
                .dType(DataType.DEAL)
                .isHided(false)
                .build()
        );
        defaultColumns.add(DataColumn.builder()
                .columnType(ColumnType.SELECT)
                .workspaceId(workspaceId)
                .columnName("Category")
                .columnIndex(10.0)
                .dType(DataType.DEAL)
                .isHided(false)
                .build()
        );
        return defaultColumns;
    }

    private List<DataColumn> generateDefaultCompanyColumns(Long workspaceId){
        List<DataColumn> defaultColumns = new ArrayList<>();

        defaultColumns.add(DataColumn.builder()
                .columnType(ColumnType.SELECT)
                .workspaceId(workspaceId)
                .columnName("Category")
                .columnIndex(1.0)
                .dType(DataType.COMPANY)
                .isHided(false)
                .build()
        );
        defaultColumns.add(DataColumn.builder()
                .columnType(ColumnType.TEXT)
                .workspaceId(workspaceId)
                .columnName("설명")
                .columnIndex(5.0)
                .dType(DataType.COMPANY)
                .isHided(false)
                .build()
        );
        return defaultColumns;
    }
}
