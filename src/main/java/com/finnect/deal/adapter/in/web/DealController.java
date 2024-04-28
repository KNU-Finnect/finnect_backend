package com.finnect.deal.adapter.in.web;

import com.finnect.cell.adapter.out.persistence.CellId;
import com.finnect.cell.application.port.in.LoadDataCellUseCase;
import com.finnect.cell.application.port.in.LoadDataColumnUseCase;
import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.state.DataCell;
import com.finnect.cell.domain.state.DataCellState;
import com.finnect.cell.domain.state.DataColumnState;
import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.deal.adapter.in.web.req.CreateDealRequest;
import com.finnect.deal.adapter.in.web.res.CreateDealResponse;
import com.finnect.deal.adapter.in.web.res.DealDetailResponse;
import com.finnect.deal.application.DealState;
import com.finnect.deal.application.port.in.CreateDealUseCase;
import com.finnect.deal.application.port.in.LoadDealUseCase;
import com.finnect.deal.domain.Deal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController {

    private final CreateDealUseCase createDealUseCase;
    private final LoadDealUseCase loadDealUseCase;
    private final LoadDataCellUseCase loadDataCellUseCase;
    private final LoadDataColumnUseCase loadDataColumnUseCase;

    @PostMapping("/workspaces/deals")
    public ResponseEntity<ApiResult<CreateDealResponse>> createDeal(
            @RequestBody CreateDealRequest createDealRequest){

        log.info(createDealRequest.toString());
        DealState dealState = createDealUseCase
                .createDeal(
                    Deal.builder()
                            .workspaceId(createDealRequest.getWorkspaceId())
                            .companyId(createDealRequest.getCompanyId())
                            .dealName(createDealRequest.getDealName())
                            .userId(createDealRequest.getUserId())
                            .build()
        );

        return new ResponseEntity<>
                (ApiUtils.success(HttpStatus.CREATED, CreateDealResponse.toDTO(dealState))
                        , HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/deal/{dealId}/details")
    public ResponseEntity<?> loadDealDetail
            (@PathVariable Long dealId){
        DealState dealState = loadDealUseCase.loadDeal
                (Deal.builder()
                .dealId(dealId)
                .build()
        );
        List<DataColumnState> columnState = loadDataColumnUseCase
                .loadDataColumns(
                DataColumn.builder()
                        .workspaceId(dealState.getWorkspaceId())
                        .build()
        );
        List<DataCellState> cellStates = loadDataCellUseCase.loadDataCells
                (DataCell.builder()
                .cellId(new CellId(dealState.getDataRowId(), null))
                .build()
        );

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK,
                DealDetailResponse.builder()
                        .dealState(dealState)
                        .columnState(columnState)
                        .cellStates(cellStates)
                        .build()
                ));
    }

}
