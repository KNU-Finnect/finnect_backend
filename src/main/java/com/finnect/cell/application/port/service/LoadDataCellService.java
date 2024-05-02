package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.LoadDataCellUseCase;
import com.finnect.cell.application.port.out.LoadDataCellPort;
import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
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
