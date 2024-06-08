package com.finnect.view.adapter.in.web.req;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PatchFilterRequest {
    private final Long viewId;
    private final List<FilterRequest> filters;
}
