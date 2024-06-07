package com.finnect.user.application.service;

import com.finnect.user.application.port.in.CheckUserExistsUseCase;
import com.finnect.user.application.port.out.ExistsUserPort;
import com.finnect.user.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class CheckUserExistsService implements CheckUserExistsUseCase {

    private final ExistsUserPort existsUserPort;

    @Override
    public boolean checkUserExists(UserId userId) {
        return existsUserPort.existsUserById(userId);
    }
}
