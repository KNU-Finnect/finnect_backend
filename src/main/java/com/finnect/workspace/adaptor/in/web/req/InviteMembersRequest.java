package com.finnect.workspace.adaptor.in.web.req;

import lombok.Getter;

import java.util.List;

@Getter
public class InviteMembersRequest {
    List<String> emails;
}
