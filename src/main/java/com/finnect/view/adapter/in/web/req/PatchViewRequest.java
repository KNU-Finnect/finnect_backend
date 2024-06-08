package com.finnect.view.adapter.in.web.req;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PatchViewRequest {
    private Long viewId;
    private String viewName;

}
