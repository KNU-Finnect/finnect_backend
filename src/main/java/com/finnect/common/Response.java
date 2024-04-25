package com.finnect.common;

public record Response(
        int status,
        Object result
) {
}
