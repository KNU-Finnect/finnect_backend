package com.finnect.view.adapter.in.web.res;


import com.finnect.view.domain.state.ViewState;
import java.util.List;
import lombok.Getter;

@Getter
public class SimpleViewInfosResponse {

    private List<SimpleViewInfo> views;

    public SimpleViewInfosResponse(List<ViewState> views) {
        
        this.views = views.stream()
                .map(view -> SimpleViewInfo.builder().viewId(view.getViewId()).viewName(view.getViewName()).build())
                .toList();;
    }
}
