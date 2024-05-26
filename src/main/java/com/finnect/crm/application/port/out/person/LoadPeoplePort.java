package com.finnect.crm.application.port.out.person;

import com.finnect.crm.domain.person.PersonState;

import java.util.List;

public interface LoadPeoplePort {

    List<PersonState> loadPeopleByCompanyId(Long companyId);
}
