package com.finnect.user.application;

import com.finnect.user.application.port.in.CheckEmailQuery;
import com.finnect.user.application.port.in.CheckUserQuery;
import com.finnect.user.application.port.out.ExistsUserPort;
import com.finnect.common.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
class CheckUserService implements CheckUserQuery, CheckEmailQuery {

    private final ExistsUserPort existsUserPort;

    @Override
    public boolean checkUserExists(UserId userId) {
        return existsUserPort.existsUserById(userId);
    }

    @Override
    public Map<String, Boolean> checkEmailsExist(Collection<String> emails) {
        Map<String, Boolean> emailsExist = new HashMap<>();

        for (String email: emails) {
            emailsExist.put(email, existsUserPort.existsUserByEmail(email));
        }

        return emailsExist;
    }
}
