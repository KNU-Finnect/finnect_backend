package com.finnect.workspace.adaptor.in.web.res.dto;

import com.finnect.workspace.MemberState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberWithoutIdDto {
    String nickname;
    String role;
    String phone;

    public MemberWithoutIdDto(MemberState state) {
        this.nickname = state.getNickname();
        this.role = state.getRole();
        this.phone = state.getPhone();
    }
}