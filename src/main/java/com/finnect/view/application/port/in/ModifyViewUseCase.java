package com.finnect.view.application.port.in;

import com.finnect.crm.domain.column.DataColumn;
import java.util.List;

public interface ModifyViewUseCase {

    void addViewColumns(List<DataColumn> columns);

    void addViewColumn(DataColumn column);

    void deleteViewColumn(DataColumn column);
}
