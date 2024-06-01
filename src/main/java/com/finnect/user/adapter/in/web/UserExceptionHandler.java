package com.finnect.user.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.user.application.port.in.exception.EmailCodeNotVerifiedException;
import com.finnect.user.application.port.in.exception.UserDuplicateException;
import com.finnect.user.application.port.out.exception.EmailCodeNotFoundException;
import com.finnect.user.application.port.out.exception.RefreshTokenNotFoundException;
import com.finnect.user.application.port.out.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<ApiResult<String>> userDuplicate(UserDuplicateException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(EmailCodeNotVerifiedException.class)
    public ResponseEntity<ApiResult<String>> emailCodeNotVerified(EmailCodeNotVerifiedException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.FORBIDDEN, e.getMessage()));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResult<String>> userNotFound(UserNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ApiResult<String>> refreshTokenNotFound(RefreshTokenNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(EmailCodeNotFoundException.class)
    public ResponseEntity<ApiResult<String>> emailCodeNotFound(EmailCodeNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
