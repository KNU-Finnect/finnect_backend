package com.finnect.view.application.port.in;

import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewDetail;
import com.finnect.view.domain.state.ViewState;
import java.util.List;

public interface LoadViewUseCase {
    ViewDetail loadViewInfo(View view);

    List<ViewState> loadDealViewList(Long workspaceId);

    List<ViewState> loadCompanyViewList(Long workspaceId);


    ViewDetail loadDealDefaultView(Long workspaceId);
}
