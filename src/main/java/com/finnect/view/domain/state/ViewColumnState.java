package com.finnect.view.domain.state;

import com.finnect.view.domain.constant.SortCondition;

public interface ViewColumnState {
    Long getColumnId();
    Long getViewId();
    Double getIndex();
    SortCondition getSort();
    Boolean isHided();
}
