package com.finnect.common.error;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResult<String>> runtimeExceptionHandler(RuntimeException e) {
        log.error(e.toString());
        log.error(String.valueOf(e.getCause()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUtils.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResult<String>> illegalStateExceptionHandler(IllegalStateException e) {
        log.error(e.toString());

        log.error(String.valueOf(e.getCause()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiUtils.fail(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResult<String>> notFoundExceptionHandler(NotFoundException e) {
        log.error(e.toString());

        log.error(String.valueOf(e.getCause()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.fail(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(NotVerifiedException.class)
    public ResponseEntity<ApiResult<String>> notVerifiedExceptionHandler(NotVerifiedException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiUtils.fail(HttpStatus.FORBIDDEN, e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResult<String>> accessDeniedExceptionHandler(AccessDeniedException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiUtils.fail(HttpStatus.FORBIDDEN, e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResult<String>> authExceptionHandler(AuthenticationException e) {
        log.error(e.toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiUtils.fail(HttpStatus.UNAUTHORIZED, e.getMessage()));
    }
}
