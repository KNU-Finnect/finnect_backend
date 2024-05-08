package com.finnect.workspace.adaptor.in.web.res;

import com.finnect.workspace.adaptor.in.web.res.dto.InvitationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InviteMembersResponse {
    List<InvitationDto> invitations;
}
