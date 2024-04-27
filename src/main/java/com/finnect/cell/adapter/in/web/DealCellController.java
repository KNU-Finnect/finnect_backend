package com.finnect.cell.adapter.in.web;

import com.finnect.cell.adapter.in.web.req.CreateDealColumnRequest;
import com.finnect.cell.adapter.in.web.res.CreateDealColumnResponse;
import com.finnect.cell.application.port.in.CreateNewColumnUseCase;
import com.finnect.cell.application.port.in.CreateNewRowUseCase;
import com.finnect.cell.domain.state.DataColumnState;
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
    public ResponseEntity<ApiResult<?>> createNewColumn(@RequestBody CreateDealColumnRequest createDealColumnRequest){
        log.info(createDealColumnRequest.toString());
        DataColumnState dataColumnState = createNewColumnUseCase.createNewColumn(createDealColumnRequest.toDomain());

        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED,CreateDealColumnResponse.toDTO(dataColumnState)),
                HttpStatus.CREATED
        );
    }
}
