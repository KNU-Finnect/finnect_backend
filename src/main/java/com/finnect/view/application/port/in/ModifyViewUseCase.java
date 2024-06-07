package com.finnect.view.application.port.in;

import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.domain.Filter;
import java.util.List;

public interface ModifyViewUseCase {

    void addViewColumns(List<DataColumnState> columns);

    void addViewColumn(DataColumnState column);

    void deleteViewColumn(DataColumnState column);

    void patchViewFilters(Long viewId, List<Filter> filters);
}
