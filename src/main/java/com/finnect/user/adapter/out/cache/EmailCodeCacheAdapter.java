package com.finnect.user.adapter.out.cache;

import com.finnect.user.adapter.out.cache.entity.EmailCodeEntity;
import com.finnect.user.adapter.out.cache.entity.EmailCodeRepository;
import com.finnect.user.application.port.out.LoadEmailCodePort;
import com.finnect.user.application.port.out.SaveEmailCodePort;
import com.finnect.user.application.port.out.error.EmailCodeNotFoundException;
import com.finnect.user.domain.state.EmailCodeState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailCodeCacheAdapter implements LoadEmailCodePort, SaveEmailCodePort {

    private final EmailCodeRepository emailCodeRepository;

    @Override
    public EmailCodeState loadEmailCode(String email) {
        return emailCodeRepository.findById(email)
                .orElseThrow(() -> new EmailCodeNotFoundException(email));
    }

    @Override
    public void saveEmailCode(EmailCodeState emailCode) {
        EmailCodeEntity emailCodeEntity = EmailCodeEntity.from(emailCode);

        emailCodeRepository.save(emailCodeEntity);
    }
}
