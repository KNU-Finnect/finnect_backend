package com.finnect.view.adapter.out;

import com.finnect.view.adapter.out.persistence.ViewEntity;
import com.finnect.view.application.port.out.LoadViewPort;
import com.finnect.view.application.port.out.SaveViewPort;
import com.finnect.view.domain.View;
import com.finnect.view.domain.state.ViewState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ViewAdapter implements SaveViewPort, LoadViewPort {
    private final ViewRepository viewRepository;
    @Override
    public ViewState saveNewView(ViewState viewState) {
        ViewEntity newView = ViewEntity.toEntity(viewState);
        viewRepository.save(newView);
        log.info(newView.toString());
        return newView.toDomain();
    }

    @Override
    public List<ViewState> saveDefaultViews(List<ViewState> views) {
        List<ViewEntity> defaultViews = views.stream()
                .map(ViewEntity::toEntity)
            .toList();
        viewRepository.saveAll(defaultViews);
        return new ArrayList<>(defaultViews);
    }

    @Override
    public View loadView(ViewState viewState) {
        ViewEntity view = viewRepository.findById(viewState.getViewId())
                .orElseThrow(() ->new IllegalArgumentException("View가 존재하지 않습니다."));
        return view.toDomain();
    }
}
