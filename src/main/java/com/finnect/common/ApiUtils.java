package com.finnect.common;

import org.springframework.http.HttpStatus;


public class ApiUtils {

    public static <T> ApiResult<T> success(HttpStatus httpStatus, T result) {
        return new ApiResult<>(httpStatus.value(), result);
    }

    public static ApiResult<Object> fail(HttpStatus httpStatus) {
        return new ApiResult<>(httpStatus.value(), null);
    }

    public static ApiResult<String> fail(HttpStatus httpStatus, String message) {
        return new ApiResult<>(httpStatus.value(), message);
    }

    public record ApiResult<T>(
            int status,
            T result
    ) {
    }
}
