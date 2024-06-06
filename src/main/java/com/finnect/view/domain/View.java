package com.finnect.view.domain;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.DataType;
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
    private DataType dType;
    @Builder
    public View(Long viewId, Long workspaceId, String viewName, Boolean isMain, List<Filter> filters,
                List<ViewColumn> viewColumns, DataType dType) {
        this.viewId = viewId;
        this.workspaceId = workspaceId;
        this.viewName = viewName;
        this.isMain = isMain;
        this.filters = filters;
        this.viewColumns = viewColumns;
        this.dType = dType;
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

    @Override
    public DataType getType() {
        return this.dType;
    }

    public void appendFilter(List<Filter> filters){
        if (filters.isEmpty()) {
            return;
        }
        this.filters.addAll(filters);
    }

    public void appendViewColumn(List<ViewColumn> viewColumns){
        if (viewColumns.isEmpty()) {
            return;
        }
        this.viewColumns.addAll(viewColumns);
    }
    public void appendViewColumn(ViewColumn viewColumn){
        if (viewColumn == null) {
            return;
        }
        this.viewColumns.add(viewColumn);
    }

    public Double getColumnLastIndex(){
        if(this.viewColumns.isEmpty()){
            return 1.0;
        }

        return viewColumns.get(viewColumns.size() - 1).getIndex() + 10;
    }
}
