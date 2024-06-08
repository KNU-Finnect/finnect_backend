package com.finnect.workspace.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InvitationResult {
    SUCCEED("SUCCEED"), FAIL("FAIL"), ALREADY_MEMBER("ALREADY_MEMBER"), YET_SIGNUP("YET_SIGNUP");

    @Getter
    private final String result;

    public static InvitationResult getInvitationResult(String result) {
        for (InvitationResult status : InvitationResult.values()) {
            if (status.getResult().equals(result)) {
                return status;
            }
        }
        return null;
    }
}
