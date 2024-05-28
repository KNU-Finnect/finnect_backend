package com.finnect.crm.application.service.person;

import com.finnect.crm.application.port.in.person.CreatePersonCommand;
import com.finnect.crm.application.port.in.person.CreatePersonUsecase;
import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithoutId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class CreatePersonService implements CreatePersonUsecase {

    private final SavePersonPort savePersonPort;

    @Override
    public PersonState createPerson(CreatePersonCommand command) {
        PersonWithoutId personWithoutId = PersonWithoutId.builder()
                .companyId(command.getCompanyId())
                .personName(command.getPersonName())
                .personRole(command.getPersonRole())
                .personEmail(command.getPersonEmail())
                .personPhone(command.getPersonPhone())
                .build();

        return savePersonPort.save(personWithoutId);
    }
}
