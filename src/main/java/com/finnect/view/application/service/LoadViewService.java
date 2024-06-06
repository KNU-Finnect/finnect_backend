package com.finnect.view.application.service;

import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.view.application.port.in.LoadViewUseCase;
import com.finnect.view.application.port.out.LoadViewPort;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewDetail;
import com.finnect.view.domain.state.ViewState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadViewService implements LoadViewUseCase {
    private final LoadViewPort loadViewPort;
    private final LoadDataColumnPort loadDataColumnPort;
    @Override
    public ViewDetail loadViewInfo(View view, List<Filter> filters) {
        view = loadViewPort.loadView(view);
        view.appendFilter(filters);
        var columns = loadDataColumnPort.loadDataColumnsOfDeal(view.getWorkspaceId());

        return ViewDetail
                .builder()
                .view(view)
                .dataColumns(columns)
        .build();
    }
}
