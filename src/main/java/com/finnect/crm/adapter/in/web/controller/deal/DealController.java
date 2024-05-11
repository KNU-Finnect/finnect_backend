package com.finnect.crm.adapter.in.web.controller.deal;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.deal.CreateDealRequest;
import com.finnect.crm.adapter.in.web.res.deal.CreateDealResponse;
import com.finnect.crm.adapter.in.web.res.deal.DealDetailResponse;
import com.finnect.crm.adapter.out.cell.persistence.CellId;
import com.finnect.crm.application.port.in.cell.LoadDataCellUseCase;
import com.finnect.crm.application.port.in.cell.LoadDataColumnUseCase;
import com.finnect.crm.application.port.in.deal.CreateDealUseCase;
import com.finnect.crm.application.port.in.deal.LoadDealUseCase;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.cell.state.DataColumnState;
import com.finnect.crm.domain.deal.Deal;
import com.finnect.crm.domain.deal.state.DealState;
import java.util.List;
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
