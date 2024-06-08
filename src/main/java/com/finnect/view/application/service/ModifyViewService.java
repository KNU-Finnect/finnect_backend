package com.finnect.view.application.service;

import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.view.application.port.in.ModifyViewUseCase;
import com.finnect.view.application.port.out.LoadViewPort;
import com.finnect.view.application.port.out.SaveViewPort;
import com.finnect.view.domain.Filter;
import com.finnect.view.domain.View;
import com.finnect.view.domain.ViewColumn;
import com.finnect.view.domain.constant.SortCondition;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ModifyViewService implements ModifyViewUseCase {

    private final LoadViewPort loadViewPort;
    private final SaveViewPort saveViewPort;

    @Override
    public void addViewColumns(List<DataColumnState> columns) {
        List<View> views = loadViewPort.loadViewsByColumn(columns);
        log.info(views.toString());
        log.info(columns.toString());
        for(int viewIndex = 0; viewIndex < views.size(); viewIndex++){
            DataColumnState column = columns.get(viewIndex);
            View view = views.get(viewIndex);

            view.appendViewColumn(
                    ViewColumn.builder()
                            .columnId(column.getColumnId())
                            .viewId(view.getViewId())
                            .index(view.getColumnLastIndex())
                            .sorting(SortCondition.NONE)
                            .hided(!view.isMain())
                    .build()
            );
        }
        saveViewPort.saveViews(new ArrayList<>(views));
    }

    @Override
    public void addViewColumn(DataColumnState column) {
        List<View> views = loadViewPort.loadViewsByColumn(column);
        views.forEach(
                view -> view.appendViewColumn(
                    ViewColumn.builder()
                            .columnId(column.getColumnId())
                            .viewId(view.getViewId())
                            .index(view.getColumnLastIndex())
                            .sorting(SortCondition.NONE)
                            .hided(!view.isMain())
                    .build())
        );
        saveViewPort.saveViews(new ArrayList<>(views));
    }

    @Override
    public void deleteViewColumn(DataColumnState column) {

    }

    @Override
    public void patchViewFilters(Long viewId, List<Filter> filters) {
        var view = loadViewPort.loadView(View.builder().viewId(viewId).build());
        if(view.isMain()){
            throw new IllegalArgumentException("Main View에 필터를 적용할 수 없습니다.");
        }
        view.setFilter(filters);
        saveViewPort.saveViews(new ArrayList<>(List.of(view)));
    }

    @Override
    public void patchViewColumn(Long viewId, ViewColumn viewColumn) {
        var view = loadViewPort.loadView(View.builder().viewId(viewId).build());

        var column = (ViewColumn) view.getViewColumns()
                .stream()
                .filter(vc -> vc.getColumnId().equals(viewColumn.getColumnId()))
        .findFirst().orElseThrow(() -> new IllegalArgumentException("Column Id가 일치하지 않습니다."));
        column.updateColumnInfo(viewColumn);
        log.info(view.toString());
        saveViewPort.saveViews(new ArrayList<>(List.of(view)));
    }

    @Override
    public void patchViewName(Long viewId, String name) {

        var view = loadViewPort.loadView(View.builder().viewId(viewId).build());
        if(view.isMain()){
            throw new IllegalArgumentException("Main View의 이름을 변경할 수 없습니다.");
        }
        view.setViewName(name);

        saveViewPort.saveViews(new ArrayList<>(List.of(view)));
    }
}
