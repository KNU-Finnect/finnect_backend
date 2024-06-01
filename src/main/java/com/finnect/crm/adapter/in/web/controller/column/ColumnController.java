package com.finnect.crm.adapter.in.web.controller.column;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.column.CreateCompanyColumnRequest;
import com.finnect.crm.adapter.in.web.req.column.CreateDealColumnRequest;
import com.finnect.crm.adapter.in.web.req.column.ModifyColumnRequest;
import com.finnect.crm.adapter.in.web.res.column.CreateCompanyColumnResponse;
import com.finnect.crm.adapter.in.web.res.column.DealColumnResponse;
import com.finnect.crm.application.port.in.column.CreateNewColumnUseCase;
import com.finnect.crm.application.port.in.column.ModifyColumnUseCase;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.user.vo.WorkspaceAuthority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ColumnController {
    private final ModifyColumnUseCase modifyColumnUseCase;
    private final CreateNewColumnUseCase createNewColumnUseCase;

    @PatchMapping("/workspaces/deals/columns")
    public ResponseEntity<ApiResult<DealColumnResponse>> modifyColumn
            (@RequestBody ModifyColumnRequest requests){
        log.info(requests.toString());
        DataColumnState modifiedColumn = modifyColumnUseCase.modifyColumnInfo(requests.toDomain());
        return ResponseEntity.ok(
                ApiUtils.success(HttpStatus.OK, DealColumnResponse.toDTO(modifiedColumn)
            ));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/workspaces/deals/columns")
    public ResponseEntity<ApiResult<DealColumnResponse>> createNewColumn(
            @RequestBody CreateDealColumnRequest createDealColumnRequest,
            @RequestHeader("Authorization") String authorizationHeader){
        log.info(authorizationHeader);

        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        DataColumnState dataColumnState = createNewColumnUseCase.createNewColumn(
                createDealColumnRequest.toDomain(
                        WorkspaceAuthority.from(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getAuthorities()).workspaceId().value()
                )
        );
        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, DealColumnResponse.toDTO(dataColumnState)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/workspaces/companies/columns")
    public ResponseEntity<ApiResult<CreateCompanyColumnResponse>> createCompanyColumn(
            @RequestBody CreateCompanyColumnRequest request){

        DataColumnState dataColumnState = createNewColumnUseCase.createNewColumn(request.toDomain());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiUtils.success(HttpStatus.CREATED, CreateCompanyColumnResponse.from(dataColumnState))
                );
    }

}
