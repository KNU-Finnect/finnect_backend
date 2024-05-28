package com.finnect.view.application.service;

import com.finnect.view.application.port.in.LoadViewUseCase;
import com.finnect.view.application.port.out.LoadViewPort;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadViewService implements LoadViewUseCase {
    private final LoadViewPort loadViewPort;
    @Override
    public ViewState loadViewInfo(View view, List<Filter> filters) {
        view = loadViewPort.loadView(view);
        view.appendFilter(filters);

        return view;
    }
}
