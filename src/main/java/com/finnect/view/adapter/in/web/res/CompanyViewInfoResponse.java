package com.finnect.view.adapter.in.web.res;

import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.view.domain.ViewDetail;
import java.util.List;
import lombok.Getter;

@Getter
public class CompanyViewInfoResponse {
    private Long viewId;
    private String viewName;
    List<FilterResponse> filters;
    List<ViewColumnResponse> viewColumns;
    List<CompanyInfoResponse> viewCompanies;
    public CompanyViewInfoResponse (ViewDetail viewState, List<CompanyCell> companyCell) {
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
        setViewDeals(companyCell);
    }
    private void setViewDeals(List<CompanyCell> companyCells){
        this.viewCompanies = companyCells.stream()
                .map(CompanyInfoResponse::new)
                .toList();
    }
}
