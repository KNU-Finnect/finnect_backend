package com.finnect.view.adapter.in.web.req;

import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewColumn;
import com.finnect.view.domain.state.FilterState;
import com.finnect.view.domain.state.ViewColumnState;
import com.finnect.view.domain.state.ViewState;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CreateViewRequest implements ViewState {
    private Long workspaceId;
    private String viewName;
    private List<FilterRequest> filters;
    private List<ViewColumnRequest> viewColumns;
    @Override
    public Long getViewId() {
        return null;
    }

    @Override
    public Long getWorkspaceId() {
        return workspaceId;
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    @Override
    public Boolean isMain() {
        return null;
    }

    @Override
    public List<FilterState> getFilters() {

        return new ArrayList<>(filters);
    }

    @Override
    public List<ViewColumnState> getViewColumns() {
        return new ArrayList<>(viewColumns);
    }

    public View toDomain(){
        return View.builder()
                .workspaceId(workspaceId)
                .viewName(viewName)
                .viewColumns(
                        viewColumns.stream()
                                .map(viewColumn -> ViewColumn.builder()
                                        .columnId(viewColumn.getColumnId())
                                        .hided(viewColumn.isHided())
                                        .sorting(viewColumn.getSorting())
                                        .index(viewColumn.getIndex())
                                        .build()
                                ).toList()
                )
                .filters(
                        filters.stream()
                        .map(filter -> Filter.builder()
                                .filterCondition(filter.getFilterCondition())
                                .value(filter.getValue())
                                .columnId(filter.getColumnId())
                                .build()).toList()
                ).build();
    }
}
