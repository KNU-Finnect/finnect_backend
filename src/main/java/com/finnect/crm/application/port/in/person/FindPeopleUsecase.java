package com.finnect.crm.application.port.in.person;

import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithCompanyState;

import java.util.List;

public interface FindPeopleUsecase {

    List<PersonState> findPeopleByCompany(Long companyId);


    List<PersonWithCompanyState> findPeopleByWorkspace(Long workspaceId);
}
