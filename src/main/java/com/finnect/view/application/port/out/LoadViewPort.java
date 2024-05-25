package com.finnect.view.application.port.out;

import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;

public interface LoadViewPort {

    View loadView(ViewState viewState);
}
