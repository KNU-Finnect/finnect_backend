package com.finnect.workspace.adaptor.in.web.res;

import com.finnect.workspace.adaptor.in.web.res.dto.WorkspaceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkspacesResponse {
    List<WorkspaceDto> workspaces;
}
