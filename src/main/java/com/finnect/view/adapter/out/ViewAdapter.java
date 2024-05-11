package com.finnect.view.adapter.out;

import com.finnect.view.adapter.out.persistence.ViewEntity;
import com.finnect.view.domain.state.ViewState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ViewAdapter implements SaveViewPort{
    private final ViewRepository viewRepository;
    @Override
    public ViewState saveNewView(ViewState viewState) {
        ViewEntity newView = ViewEntity.toEntity(viewState);
        viewRepository.save(newView);
        log.info(newView.toString());
        return newView.toDomain();
    }
}
