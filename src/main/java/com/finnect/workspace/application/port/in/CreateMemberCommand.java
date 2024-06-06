package com.finnect.workspace.application.port.in;

import com.finnect.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class CreateMemberCommand extends SelfValidating<CreateMemberCommand> {

    @NotNull(message = "사용자의 ID는 null일 수 없습니다.")
    private final Long userId;
    @NotNull(message = "워크스페이스의 ID는 null일 수 없습니다.")
    private final Long workspaceId;
    @NotEmpty(message = "닉네임은 빈 문자열이거나 공백일 수 없습니다.")
    private final String nickname;

    @Builder
    public CreateMemberCommand(Long userId, Long workspaceId, String nickname) {
        this.userId = userId;
        this.workspaceId = workspaceId;
        this.nickname = nickname;
        isSmallerThan("nickname", this.nickname, 50);
        this.validateSelf();
    }


    private void isSmallerThan(String field, String name, int size) {
        if (name.getBytes(StandardCharsets.UTF_8).length > size)
            throw new RuntimeException(field + "은/는 " + size + "byte보다 작거나 같아야 합니다.");
    }
}
