package com.finnect.workspace.application.port.in;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class UpdateMemberCommand extends SelfValidating<UpdateMemberCommand> {

    @NotNull(message = "사용자의 ID는 null일 수 없습니다.")
    private final Long userId;
    @NotNull(message = "워크스페이스의 ID는 null일 수 없습니다.")
    private final Long workspaceId;
    private final String nickname;
    private final String role;
    private final String phone;

    @Builder
    public UpdateMemberCommand(Long userId, Long workspaceId, String nickname, String role, String phone) {
        this.userId = userId;
        this.workspaceId = workspaceId;

        this.nickname = nickname;
        if (nickname != null)
            isSmallerThan("nickname", this.nickname, 50);

        this.role = role;
        if (role != null)
            isSmallerThan("role", this.role, 50);

        this.phone = phone;
        if (phone != null)
            isSmallerThan("phone", this.phone, 50);

        this.validateSelf();
    }

    private void isSmallerThan(String field, String name, int size) {
        if (name.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException(field + "은/는 " + size + "byte보다 작거나 같아야 합니다.");
    }
}
