package com.finnect.user.application;

import com.finnect.user.application.port.in.CheckDefaultWorkspaceQuery;
import com.finnect.user.application.port.in.GetPersonalNameQuery;
import com.finnect.user.application.port.out.LoadUserPort;
import com.finnect.user.domain.User;
import com.finnect.common.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements GetPersonalNameQuery, CheckDefaultWorkspaceQuery
{
    private final LoadUserPort loadUserPort;

    @Override
    public String getPersonalName(UserId userId) {
        User user = User.from(loadUserPort.loadUser(userId));

        return user.getLastName() + user.getFirstName();
    }

    @Override
    public boolean checkDefaultWorkspace(UserId userId) {
        User user = User.from(loadUserPort.loadUser(userId));

        return user.hasDefaultWorkspace();
    }
}
