package com.finnect.crm.adapter.in.web.controller.cell;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.adapter.in.web.req.cell.ModifyCellRequest;
import com.finnect.crm.application.port.in.cell.ModifyDataCellUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@Slf4j
public class DealCellController {
    private final ModifyDataCellUseCase modifyDataCellUseCase;
    @PatchMapping("/workspaces/cells")
    public ResponseEntity<ApiResult<Object>> patchCell(@RequestBody ModifyCellRequest request){
        modifyDataCellUseCase.modifyCellInfo(request.toDomain());

        return ResponseEntity.ok(
                ApiUtils.success(HttpStatus.OK, null)
        );
    }
}
