package com.finnect.view.application.port.out;

import com.finnect.crm.domain.column.DataType;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;
import java.util.List;

public interface LoadViewPort {

    View loadView(ViewState viewState);

    List<View> loadViewsByColumn(List<DataColumnState> columns);


    List<View> loadViewsByColumn(DataColumnState columns);

    List<View> loadDealViewsByWorkspaceId(Long workspaceId, DataType dataType);

    View loaDefaultDealViewByWorkspaceId(Long workspaceId);

    View loaDefaultCompanyViewByWorkspaceId(Long workspaceId);
}
