package com.finnect.view.adapter.in.web.controller;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellPort;
import com.finnect.view.adapter.in.web.req.CreateViewRequest;
import com.finnect.view.adapter.in.web.res.CreateViewResponse;
import com.finnect.view.adapter.out.SaveViewPort;
import com.finnect.view.application.port.in.CreateViewUseCase;
import com.finnect.view.domain.state.ViewState;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

@RequiredArgsConstructor
public class ViewController {
    private final LoadDealWithCellPort loadDealWithCellPort;
    private final CreateViewUseCase createViewUseCase;
    @PostMapping("/workspaces/views")
    public ResponseEntity<ApiResult<CreateViewResponse>> createView(@RequestBody CreateViewRequest request){
//        loadDealWithCellPort.loadDealsWithCellsByFilter(null);
        log.info(request.toString());
        ViewState newView = createViewUseCase.createNewView(request.toDomain());
        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED, new CreateViewResponse(newView)),
                HttpStatus.CREATED);
    }

}
