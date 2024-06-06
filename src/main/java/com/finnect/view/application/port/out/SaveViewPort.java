package com.finnect.view.application.port.out;

import com.finnect.view.domain.state.ViewState;
import java.util.List;

public interface SaveViewPort {

    ViewState saveNewView(ViewState viewState);
    List<ViewState> saveDefaultViews(List<ViewState> views);
}
