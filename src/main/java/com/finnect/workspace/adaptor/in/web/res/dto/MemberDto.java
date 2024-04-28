package com.finnect.workspace.adaptor.in.web.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    Long userId;
    String nickname;
    String role;
    String phone;
}
