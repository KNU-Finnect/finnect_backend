package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.LoadDataColumnUseCase;
import com.finnect.cell.application.port.out.LoadDataColumnPort;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataColumnState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadDataColumnService implements LoadDataColumnUseCase {
    private final LoadDataColumnPort dataColumnPort;

    @Override
    public List<DataColumnState> loadDataColumns(DataColumn dataColumn) {
        List<DataColumn> dataColumns = dataColumnPort.loadDataColumnsByWorkspaceId(dataColumn);
        log.info(dataColumns.toString());
        return new ArrayList<>(dataColumns);
    }
}
