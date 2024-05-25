package com.finnect.view.adapter.in.web.req;

import com.finnect.view.domain.constant.SortCondition;
import com.finnect.view.domain.state.ViewColumnState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@Getter
public class ViewColumnRequest  implements ViewColumnState {
    private Long columnId;
    private Double index;
    private Boolean hided;
    private SortCondition sorting;

    @Override
    public Long getViewId() {
        return null;
    }

    @Override
    public SortCondition getSort() {
        return sorting;
    }

    @Override
    public Boolean isHided() {
        return hided;
    }
}
