package com.finnect.workspace.adaptor.in.web.res;

import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceWithoutIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkspaceResponse {
    WorkspaceWithoutIdDto workspace;
}
