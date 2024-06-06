package com.finnect.view.application.port.out;

import com.finnect.view.domain.state.ViewState;
import java.util.List;

public interface SaveViewPort {

    ViewState saveNewView(ViewState viewState);

    ViewState saveViews(List<ViewState> viewStates);

    List<ViewState> saveDefaultViews(List<ViewState> views);
}
