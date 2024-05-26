package com.finnect.view.domain;

import com.finnect.view.domain.state.FilterState;
import com.finnect.view.domain.state.ViewColumnState;
import com.finnect.view.domain.state.ViewState;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.ToString;

@ToString
public class View implements ViewState {
    private Long viewId;
    //FK
    private Long workspaceId;
    private String viewName;
    private Boolean isMain;
    private List<Filter> filters;
    private List<ViewColumn> viewColumns;

    @Builder
    public View(Long viewId, Long workspaceId, String viewName, Boolean isMain, List<Filter> filters,
                List<ViewColumn> viewColumns) {
        this.viewId = viewId;
        this.workspaceId = workspaceId;
        this.viewName = viewName;
        this.isMain = isMain;
        this.filters = filters;
        this.viewColumns = viewColumns;
    }

    @Override
    public Long getViewId() {
        return this.viewId;
    }

    @Override
    public Long getWorkspaceId() {
        return this.workspaceId;
    }

    @Override
    public String getViewName() {
        return this.viewName;
    }

    @Override
    public Boolean isMain() {
        return this.isMain;
    }

    @Override
    public List<FilterState> getFilters() {
        return new ArrayList<>(filters);
    }

    @Override
    public List<ViewColumnState> getViewColumns() {
        return new ArrayList<>(viewColumns);
    }

    public void appendFilter(List<Filter> filters){
        if (filters.isEmpty()) {
            return;
        }
        this.filters.addAll(filters);
    }
}
