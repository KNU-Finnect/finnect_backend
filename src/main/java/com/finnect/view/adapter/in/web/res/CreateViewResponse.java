package com.finnect.view.adapter.in.web.res;

import com.finnect.view.domain.state.ViewState;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateViewResponse {

    private Long viewId;
    private Long workspaceId;
    private List<FilterResponse> filters;
    private List<ViewColumnResponse> viewColumns;

    public CreateViewResponse(ViewState viewState) {
        this.viewId = viewState.getViewId();
        this.workspaceId = viewState.getWorkspaceId();
        this.filters = viewState.getFilters().stream()
                .map(filter -> FilterResponse.builder()
                        .viewId(filter.getViewId())
                        .columnId(filter.getColumnId())
                        .condition(filter.getFilterCondition())
                        .value(filter.getValue())
                        .filterId(filter.getFilterId())
                        .build()
                ).toList();
        this.viewColumns = viewState.getViewColumns().stream()
                .map(viewColumn -> ViewColumnResponse.builder()
                        .index(viewColumn.getIndex())
                        .hided(viewColumn.isHided())
                        .columnId(viewColumn.getColumnId())
                        .sorting(viewColumn.getSort())
                        .build())
                .toList();
    }
}
