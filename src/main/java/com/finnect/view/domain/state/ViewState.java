package com.finnect.view.domain.state;

import com.finnect.view.domain.ViewColumn;
import java.util.List;

public interface ViewState {
    Long getViewId();
    Long getWorkspaceId();
    String getViewName();
    Boolean isMain();
    List<FilterState> getFilters();
    List<ViewColumnState> getViewColumns();
}
