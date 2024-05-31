package com.finnect.crm.application.service.column;

import com.finnect.crm.application.port.in.column.LoadDataColumnUseCase;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
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
