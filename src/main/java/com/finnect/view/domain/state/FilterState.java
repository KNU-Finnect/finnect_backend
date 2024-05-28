package com.finnect.view.domain.state;

import com.finnect.view.domain.constant.FilterCondition;

public interface FilterState {
    Long getFilterId();
    Long getViewId();
    Long getColumnId();
    FilterCondition getFilterCondition();
    String getValue();
}
