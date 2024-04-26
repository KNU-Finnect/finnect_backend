package com.finnect.user.adapter.in.web.request;

public record SignupRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}