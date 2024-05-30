package com.finnect.common;

import org.springframework.http.HttpStatus;


public class ApiUtils {

    public static <T> ApiResult<T> success(HttpStatus httpStatus, T result) {
        return new ApiResult<>(httpStatus.value(), result);
    }

    public static ApiResult<Object> fail(HttpStatus httpStatus) {
        return new ApiResult<>(httpStatus.value(), null);
    }

    public record ApiResult<T>(
            int status,
            T result
    ) {
    }
}
