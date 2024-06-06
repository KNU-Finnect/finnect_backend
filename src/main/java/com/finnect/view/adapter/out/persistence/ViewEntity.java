package com.finnect.view.adapter.out.persistence;

import com.finnect.crm.domain.column.DataType;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewColumn;
import com.finnect.view.domain.state.FilterState;
import com.finnect.view.domain.state.ViewColumnState;
import com.finnect.view.domain.state.ViewState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.ToString;

@Entity(name = "view")
@ToString
public class ViewEntity implements ViewState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viewId;

    //FK
    private Long workspaceId;

    private String viewName;
    private Boolean isMain;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "view_id")
    private List<FilterEntity> filters;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "view_id")
    private List<ViewColumnEntity> viewColumns;

    @Enumerated(EnumType.STRING)
    private DataType dType;

    protected ViewEntity() {
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
        return null;
    }

    @Override
    public DataType getType() {
        return this.dType;
    }

    @Builder
    public ViewEntity(Long viewId, Long workspaceId, String viewName, Boolean isMain, List<FilterState> filters,
                      List<ViewColumnState> viewColumns, DataType dType) {
        this.viewId = viewId;
        this.workspaceId = workspaceId;
        this.viewName = viewName;
        this.isMain = isMain;
        this.filters = filters.stream()
                .map(filter -> FilterEntity.builder()
                        .value(filter.getValue())
                        .filterCondition(filter.getFilterCondition())
                        .columnId(filter.getColumnId()).build())
                .toList();

        this.viewColumns = viewColumns.stream()
                .map(viewColumn -> ViewColumnEntity.builder()
                        .columnId(viewColumn.getColumnId())
                        .showIndex(viewColumn.getIndex())
                        .hided(viewColumn.isHided())
                        .sorting(viewColumn.getSort())
                        .view(this)
                        .build()
                ).toList();
        this.dType = dType;
    }

    public static ViewEntity toEntity(ViewState viewState){
        return ViewEntity.builder()
                .viewId(viewState.getViewId())
                .workspaceId(viewState.getWorkspaceId())
                .viewName(viewState.getViewName())
                .isMain(false)
                .filters(viewState.getFilters())
                .viewColumns(viewState.getViewColumns())
                .dType(viewState.getType())
            .build();
    }

    public View toDomain(){
        return View.builder()
                .viewId(this.viewId)
                .viewName(this.viewName)
                .workspaceId(this.getWorkspaceId())
                .dType(this.dType)
                .viewColumns(this.viewColumns.stream()
                        .map(viewColumn -> ViewColumn.builder()
                                .viewId(this.getViewId())
                                .columnId(viewColumn.getColumnId())
                                .index(viewColumn.getIndex())
                                .hided(viewColumn.isHided())
                                .sorting(viewColumn.getSort())
                                .build())
                        .toList()
                )
                .filters(this.filters.stream()
                        .map(filter -> Filter.builder()
                                .viewId(this.getViewId())
                                .value(filter.getValue())
                                .filterCondition(filter.getFilterCondition())
                                .columnId(filter.getColumnId())
                                .id(filter.getFilterId())
                                .build())
                        .toList()
                ).build();
    }
}
