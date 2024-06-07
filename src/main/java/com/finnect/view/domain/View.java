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
        return filters == null ? new ArrayList<>() : new ArrayList<>(filters);
    }

    @Override
    public List<ViewColumnState> getViewColumns() {
        return viewColumns == null ? new ArrayList<>() : new ArrayList<>(viewColumns);
    }

    @Override
    public DataType getType() {
        return this.dType;
    }

    public void setFilter(List<Filter> filters){
        this.filters = filters;
    }

    public void appendViewColumn(List<ViewColumn> viewColumns){
        if (this.viewColumns.isEmpty()) {
            this.viewColumns = new ArrayList<>();
        }
        this.viewColumns.addAll(viewColumns);
    }
    public void appendViewColumn(ViewColumn viewColumn){
        if (this.viewColumns.isEmpty()) {
            this.viewColumns = new ArrayList<>();
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
