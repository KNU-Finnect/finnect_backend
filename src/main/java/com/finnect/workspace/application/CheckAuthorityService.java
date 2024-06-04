package com.finnect.workspace.application;

import com.finnect.workspace.application.port.in.CheckAuthorityUsecase;
import com.finnect.workspace.application.port.out.SearchMemberPort;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
class CheckAuthorityService implements CheckAuthorityUsecase {
    private final SearchMemberPort searchMemberPort;

    @Override
    public boolean checkAuthorityOfUser(Long userId, Long workspaceId) {
        return searchMemberPort.searchMember(userId, workspaceId);
    }
}
