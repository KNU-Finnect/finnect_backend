package com.finnect.view.adapter.in.web.res;

import com.finnect.crm.domain.deal.DealCell;
import com.finnect.view.domain.ViewDetail;
import java.util.List;
import lombok.Getter;

@Getter
public class DealViewInfoResponse {
    private Long viewId;
    private String viewName;
    List<FilterResponse> filters;
    List<ViewColumnResponse> viewColumns;
    List<DealInfoResponse> viewDeals;
    public DealViewInfoResponse(ViewDetail viewState, List<DealCell> dealCells) {
        this.viewId = viewState.getViewId();
        this.viewName = viewState.getViewName();
        this.filters = viewState.getFilters().stream()
                .map(filter -> FilterResponse.builder()
                        .columnId(filter.getColumnId())
                        .condition(filter.getFilterCondition())
                        .value(filter.getValue())
                        .filterId(filter.getFilterId())
                        .build()
                ).toList();
        this.viewColumns = viewState.getViewColumns().stream()
                .map(viewColumn -> ViewColumnResponse.builder()
                        .index(viewColumn.getIndex())
                        .hided(viewColumn.getHided())
                        .columnId(viewColumn.getColumnId())
                        .sorting(viewColumn.getSorting())
                        .columnType(viewColumn.getColumnType())
                        .build())
                .toList();
        setViewDeals(dealCells);
    }
    private void setViewDeals(List<DealCell> dealCells){
        this.viewDeals = dealCells.stream()
                .map(DealInfoResponse::new)
                .toList();
    }
}
