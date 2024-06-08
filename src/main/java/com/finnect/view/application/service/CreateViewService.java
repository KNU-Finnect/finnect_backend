package com.finnect.view.application.service;

import com.finnect.crm.application.port.in.column.LoadDataColumnUseCase;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.crm.domain.column.DataType;
import com.finnect.view.application.port.out.SaveViewPort;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewColumn;
import com.finnect.view.domain.constant.SortCondition;
import com.finnect.view.domain.state.ViewState;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateViewService implements CreateViewUseCase {

    private final LoadDataColumnUseCase loadDataColumnUseCase;

    private final SaveViewPort saveViewPort;

    @Override
    public ViewState createNewView(View view) {
        log.info(view.toString());

        // Column들을 전부 가져온다.
        List<DataColumnState> columns = loadDataColumnUseCase.loadDataColumns(
                view.getWorkspaceId(),
                view.getType()
        );

        // 가져온 Column들로 View Column들을 생성한다.
        List<ViewColumn> viewColumns = columns.stream()
                .map(it -> ViewColumn.builder()
                        .columnId(it.getColumnId())
                        .viewId(view.getViewId())
                        .index(view.getColumnLastIndex())
                        .sorting(SortCondition.NONE)
                        .hided(false)
                        .build())
                .toList();

        // 생성한 View Column들을 View에 추가한다.
        view.appendViewColumn(viewColumns);

        // View를 저장한다.
        return saveViewPort.saveNewView(view);
    }

    @Override
    @Transactional
    public void createDefaultView(Long workspaceId) {
        List<View> defaultView = new ArrayList<>();

        log.info(String.valueOf(workspaceId));

        defaultView.add(
                View.builder()
                        .workspaceId(workspaceId)
                        .viewName("Company")
                        .isMain(true)
                        .dType(DataType.COMPANY)
                .build());

        defaultView.add(
                View.builder()
                        .workspaceId(workspaceId)
                        .viewName("Deal")
                        .isMain(true)
                        .dType(DataType.DEAL)
                .build()
        );

        log.info("default View Generated");
        log.info(defaultView.toString());
        saveViewPort.saveDefaultViews(new ArrayList<>(defaultView));
    }
}
