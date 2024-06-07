package com.finnect.view.adapter.in.web.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SimpleViewInfo {
    private Long viewId;
    private String viewName;
}
