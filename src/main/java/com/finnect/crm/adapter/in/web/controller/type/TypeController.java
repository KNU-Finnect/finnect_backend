package com.finnect.crm.adapter.in.web.controller.type;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.crm.domain.column.ColumnType;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TypeController {


    @GetMapping("/types")
    public ResponseEntity<ApiResult<List<ColumnType>>> getTypes(){
        List<ColumnType> types = Arrays.stream(ColumnType.values()).toList();

        return ResponseEntity.ok(
                ApiUtils.success(HttpStatus.OK, types));
    }

    @GetMapping("/types/{typeName}")
    public ResponseEntity<ApiResult<ColumnType>> getTypes(@PathVariable String typeName){
        ColumnType type = ColumnType.getColumnType(typeName.toUpperCase());

        return ResponseEntity.ok(
                ApiUtils.success(HttpStatus.OK, type));
    }
}
