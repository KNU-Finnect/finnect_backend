package com.finnect.view.adapter.in.web.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PatchViewColumnRequest {
    private Long viewId;
    private ViewColumnRequest column;
}
