package com.finnect.view.application.service;

import com.finnect.view.adapter.out.SaveViewPort;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateViewService implements CreateViewUseCase {
    private final SaveViewPort saveViewPort;
    @Override
    public ViewState createNewView(View view) {
        log.info(view.toString());
        return saveViewPort.saveNewView(view);
    }
}
