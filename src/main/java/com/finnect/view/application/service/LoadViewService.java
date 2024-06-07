package com.finnect.view.application.service;

import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.view.application.port.in.LoadViewUseCase;
import com.finnect.view.application.port.out.LoadViewPort;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewDetail;
import com.finnect.view.domain.state.ViewState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadViewService implements LoadViewUseCase {
    private final LoadViewPort loadViewPort;
    private final LoadDataColumnPort loadDataColumnPort;
    @Override
    public ViewDetail loadViewInfo(View view) {
        view = loadViewPort.loadView(view);
        var columns = loadDataColumnPort.loadDataColumnsOfCompany(view.getWorkspaceId());
        return ViewDetail
                .builder()
                .view(view)
                .dataColumns(columns)
        .build();
    }

    @Override
    public List<ViewState> loadViewList(Long workspaceId) {
        return new ArrayList<>(loadViewPort.loadDealViewsByWorkspaceId(workspaceId));
    }

    @Override
    public ViewDetail loadDealDefaultView(Long workspaceId) {
        var view = loadViewPort.loaDefaultDealViewByWorkspaceId(workspaceId);
        log.info(String.valueOf(view));
        var columns = loadDataColumnPort.loadDataColumnsOfDeal(view.getWorkspaceId());
        log.info(String.valueOf(columns));
        return ViewDetail.builder()
                .view(view)
                .dataColumns(columns)
            .build();
    }
}
