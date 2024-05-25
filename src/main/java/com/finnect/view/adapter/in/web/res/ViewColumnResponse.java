package com.finnect.view.adapter.in.web.res;

import com.finnect.view.domain.constant.SortCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ViewColumnResponse {
    private Long columnId;
    private Double index;
    private Boolean hided;
    private SortCondition sorting;
}
