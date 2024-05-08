package com.finnect.workspace.adaptor.in.web.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindMembersResponse {
    List<MemberWithoutIdDto> members;
}
