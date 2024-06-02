package com.finnect.common.error;

import com.finnect.common.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiUtils.ApiResult<String>> runtimeExceptionHandler(RuntimeException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUtils.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiUtils.ApiResult<String>> illegalStateExceptionHandler(IllegalStateException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiUtils.fail(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiUtils.ApiResult<String>> notFoundExceptionHandler(NotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.fail(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
