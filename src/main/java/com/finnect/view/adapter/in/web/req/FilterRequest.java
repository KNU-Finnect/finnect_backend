package com.finnect.view.adapter.in.web.req;

import com.finnect.view.domain.constant.FilterCondition;
import com.finnect.view.domain.state.FilterState;
import com.finnect.view.domain.state.ViewColumnState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Builder
@Setter
@ToString
public class FilterRequest implements FilterState {
    private Long columnId;
    private FilterCondition condition;
    private String value;

    @Override
    public Long getFilterId() {
        return null;
    }

    @Override
    public Long getViewId() {
        return null;
    }

    @Override
    public Long getColumnId() {
        return this.columnId;
    }

    @Override
    public FilterCondition getFilterCondition() {
        return this.condition;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
