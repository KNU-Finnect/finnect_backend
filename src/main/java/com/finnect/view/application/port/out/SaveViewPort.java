package com.finnect.view.application.port.out;

import com.finnect.view.domain.state.ViewState;

public interface SaveViewPort {

    ViewState saveNewView(ViewState viewState);
}
