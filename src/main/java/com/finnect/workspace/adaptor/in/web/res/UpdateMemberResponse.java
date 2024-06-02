package com.finnect.workspace.adaptor.in.web.res;

import com.finnect.workspace.adaptor.in.web.res.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMemberResponse {
    MemberDto member;

    public static UpdateMemberResponse of(MemberDto memberDto) {
        return new UpdateMemberResponse(memberDto);
    }
}
