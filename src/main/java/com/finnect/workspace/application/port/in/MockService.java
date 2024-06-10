package com.finnect.workspace.application.port.in;

import com.finnect.common.vo.UserId;
import com.finnect.common.vo.WorkspaceId;
import org.springframework.stereotype.Service;

// 이 서비스는 임시용으로, 이 서비스가 구현하는 유즈케이스 및 쿼리를 실제로 구현하면 이 서비스를 삭제할 것
@Service
public class MockService implements CheckWorkspaceQuery {

    @Override
    public boolean checkWorkspace(UserId userId, WorkspaceId workspaceId) {
        return true;
    }
}
