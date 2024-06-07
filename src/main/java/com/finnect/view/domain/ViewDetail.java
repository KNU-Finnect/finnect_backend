package com.finnect.view.domain;

import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.DataType;
import com.finnect.view.domain.state.FilterState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewDetail {
    private Long viewId;
    //FK
    private String viewName;
    private Boolean isMain;
    private List<FilterState> filters;
    private List<ViewColumnDetail> viewColumns;
    private DataType dType;

    @Builder
    public ViewDetail(View view, List<DataColumn> dataColumns) {
        this.viewId = view.getViewId();
        this.viewName = view.getViewName();
        this.isMain = view.isMain();
        this.filters = new ArrayList<>(view.getFilters());

        var viewColumnMap = new HashMap<Long, ViewColumnDetail>();
        view.getViewColumns()
                .forEach( data -> {
                            viewColumnMap.put(
                                    data.getColumnId(),
                                            ViewColumnDetail.builder()
                                                .viewId(data.getViewId())
                                                .columnId(data.getColumnId())
                                                    .hided(data.isHided())
                                                    .sorting(data.getSort())
                                                    .index(data.getIndex())
                                    .build());
                        }
                );
        dataColumns
                .forEach( data -> {
                    var viewColumnDetail = viewColumnMap.get(data.getColumnId());
                    viewColumnDetail.setColumnType(data.getColumnType());
                    viewColumnDetail.setColumnName(data.getColumnName());
                });
        this.viewColumns = new ArrayList<>(viewColumnMap.values());
        this.dType = view.getType();
    }
}
