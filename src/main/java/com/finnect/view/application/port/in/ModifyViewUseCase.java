package com.finnect.view.application.port.in;

import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.ViewColumn;
import java.util.List;

public interface ModifyViewUseCase {

    void addViewColumns(List<DataColumnState> columns);

    void addViewColumn(DataColumnState column);

    void deleteViewColumn(DataColumnState column);

    void patchViewFilters(Long viewId, List<Filter> filters);
    void patchViewColumn(Long viewId, ViewColumn viewColumn);

    void patchViewName(Long viewId, String name);
}
