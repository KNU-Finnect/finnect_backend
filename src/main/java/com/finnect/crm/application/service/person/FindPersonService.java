package com.finnect.crm.application.service.person;

import com.finnect.crm.application.port.in.person.FindPeopleUsecase;
import com.finnect.crm.application.port.out.person.LoadPeoplePort;
import com.finnect.crm.domain.person.PersonState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
class FindPersonService implements FindPeopleUsecase {

    private final LoadPeoplePort loadPeoplePort;


    @Override
    public List<PersonState> findPeople(Long companyId) {
        //TODO: Company가 존재하는지 검사

        return loadPeoplePort.loadPeopleByCompanyId(companyId);
    }
}
