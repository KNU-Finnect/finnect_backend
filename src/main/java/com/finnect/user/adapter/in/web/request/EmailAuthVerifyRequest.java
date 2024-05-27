package com.finnect.user.adapter.in.web.request;

public record EmailAuthVerifyRequest(
        String email,
        Integer codeNumber
) {
}
