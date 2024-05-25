package com.finnect.workspace.adaptor.in.web.req;

import lombok.Getter;

@Getter
public class CreateMemberRequest {
    String nickname;
    String role;
    String phone;
}
