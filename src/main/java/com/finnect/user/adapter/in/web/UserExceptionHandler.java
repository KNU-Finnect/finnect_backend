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
    public ResponseEntity<ApiResult<Object>> userDuplicate(UserDuplicateException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.CONFLICT));
    }

    @ExceptionHandler(EmailCodeNotVerifiedException.class)
    public ResponseEntity<ApiResult<Object>> emailCodeNotVerified(EmailCodeNotVerifiedException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.FORBIDDEN));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResult<Object>> userNotFound(UserNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ApiResult<Object>> refreshTokenNotFound(RefreshTokenNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(EmailCodeNotFoundException.class)
    public ResponseEntity<ApiResult<Object>> emailCodeNotFound(EmailCodeNotFoundException e) {
        log.error(e.toString());

        return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
    }
}
