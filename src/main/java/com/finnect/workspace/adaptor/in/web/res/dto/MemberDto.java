package com.finnect.workspace.adaptor.in.web.res.dto;

import com.finnect.workspace.domain.state.MemberState;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
public class MemberDto {
    Long userId;
    String nickname;
    String role;
    String phone;

    public static MemberDto from(MemberState memberState) {
        return MemberDto.builder()
                .userId(memberState.getUserId())
                .nickname(memberState.getNickname())
                .role(memberState.getRole())
                .phone(memberState.getPhone())
                .build();
    }
}