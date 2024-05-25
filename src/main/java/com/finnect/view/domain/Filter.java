package com.finnect.view.domain;

import com.finnect.view.domain.constant.FilterCondition;
import lombok.Builder;
import lombok.ToString;

@ToString
public class Filter implements com.finnect.view.domain.state.FilterState {

    private Long id;
    private Long viewId;
    private Long columnId;
    private FilterCondition filterCondition;
    private String value;

    @Builder
    public Filter(Long id, Long viewId, Long columnId, FilterCondition filterCondition, String value) {
        this.id = id;
        this.viewId = viewId;
        this.columnId = columnId;
        this.filterCondition = filterCondition;
        this.value = value;
    }

    @Override
    public Long getFilterId() {
        return this.id;
    }

    @Override
    public Long getViewId() {
        return this.viewId;
    }

    @Override
    public Long getColumnId() {
        return this.columnId;
    }

    @Override
    public FilterCondition getFilterCondition() {
        return this.filterCondition;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
