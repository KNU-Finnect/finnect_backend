package com.finnect.crm.adapter.in.web.controller.cell;

import com.finnect.crm.adapter.in.web.req.cell.CreateCompanyColumnRequest;
import com.finnect.crm.adapter.in.web.req.cell.CreateDealColumnRequest;
import com.finnect.crm.adapter.in.web.res.cell.CreateCompanyColumnResponse;
import com.finnect.crm.adapter.in.web.res.cell.DealColumnResponse;
import com.finnect.crm.application.port.in.cell.CreateNewColumnUseCase;
import com.finnect.crm.domain.cell.state.DataColumnState;
import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealCellController {

    private final CreateNewColumnUseCase createNewColumnUseCase;

    @PostMapping("/workspaces/deals/columns")
    public ResponseEntity<ApiResult<DealColumnResponse>> createNewColumn(@RequestBody CreateDealColumnRequest createDealColumnRequest){
        log.info(createDealColumnRequest.toString());
        DataColumnState dataColumnState = createNewColumnUseCase.createNewColumn(createDealColumnRequest.toDomain());

        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, DealColumnResponse.toDTO(dataColumnState)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/workspaces/companies/columns")
    public ResponseEntity<ApiResult<CreateCompanyColumnResponse>> createCompanyColumn(@RequestBody CreateCompanyColumnRequest request){
        DataColumnState dataColumnState = createNewColumnUseCase.createNewColumn(request.toDomain());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiUtils.success(HttpStatus.CREATED, CreateCompanyColumnResponse.from(dataColumnState))
                );
    }
}
