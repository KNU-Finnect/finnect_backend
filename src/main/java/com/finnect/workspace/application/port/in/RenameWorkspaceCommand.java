package com.finnect.workspace.application.port.in;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class RenameWorkspaceCommand extends SelfValidating<RenameWorkspaceCommand> {

    @NotNull(message = "워크스페이스의 ID는 null일 수 없습니다.")
    private final Long workspaceId;
    @NotEmpty(message = "워크스페이스의 이름은 빈 문자열이거나 공백일 수 없습니다.")
    private final String workspaceName;

    @Builder
    public RenameWorkspaceCommand(Long workspaceId, String newName) {
        this.workspaceId = workspaceId;
        this.workspaceName = newName;
        isSmallerThan(this.workspaceName, 50);
        this.validateSelf();
    }

    private void isSmallerThan(String name, int size) {
        if (name.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException("워크스페이스의 이름은 " + size + "byte보다 작거나 같아야 합니다.");
    }
}
