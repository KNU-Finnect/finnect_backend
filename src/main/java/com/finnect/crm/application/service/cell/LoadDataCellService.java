package com.finnect.crm.application.service.cell;

import com.finnect.crm.application.port.in.cell.LoadDataCellUseCase;
import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDataCellService implements LoadDataCellUseCase {
    private final LoadDataCellPort dataCellPort;
    @Override
    public List<DataCellState> loadDataCells(DataCell dataCell) {
        List<DataCell> dataCells = dataCellPort.loadDataCellsByRowId(dataCell);
        log.info(dataCells.toString());
        return new ArrayList<>(dataCells);
    }
}
