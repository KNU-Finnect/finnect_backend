package com.finnect.view.application.port.in;

import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;

public interface CreateViewUseCase {
    ViewState createNewView(View view);
    void createDefaultView(Long workspaceId);
}
