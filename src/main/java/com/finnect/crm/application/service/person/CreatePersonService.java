package com.finnect.crm.application.service.person;

import com.finnect.common.error.CustomException;
import com.finnect.crm.application.port.in.person.CreatePersonCommand;
import com.finnect.crm.application.port.in.person.CreatePersonUsecase;
import com.finnect.crm.application.port.out.company.CheckCompanyQuery;
import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithoutId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class CreatePersonService implements CreatePersonUsecase {

    private final SavePersonPort savePersonPort;
    private final CheckCompanyQuery checkCompanyQuery;

    @Override
    public PersonState createPerson(CreatePersonCommand command) {
        if (!checkCompanyQuery.checkCompanyExists(command.getCompanyId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ID " + command.getCompanyId() + "은/는 존재하지 않는 회사입니다.");

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
