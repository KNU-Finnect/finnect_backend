package com.finnect.view.adapter.in.web.res;

import com.finnect.view.domain.constant.FilterCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FilterResponse {
    private Long filterId;
    private Long columnId;
    private FilterCondition condition;
    private String value;
}
