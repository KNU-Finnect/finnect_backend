package com.finnect.crm.application.service.person;

import com.finnect.crm.application.port.in.person.DeletePersonUsecase;
import com.finnect.crm.application.port.out.person.DeletePersonPort;
import com.finnect.crm.application.port.out.person.LoadPersonPort;
import com.finnect.common.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class DeletePersonService implements DeletePersonUsecase {

    private final LoadPersonPort loadPersonPort;
    private final DeletePersonPort deletePersonPort;

    @Override
    public boolean delete(Long personId) {
        if (loadPersonPort.loadById(personId) == null)
            throw new NotFoundException("존재하지 않는 Person입니다.");

        return deletePersonPort.delete(personId);
    }
}
