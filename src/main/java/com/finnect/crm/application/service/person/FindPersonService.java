package com.finnect.crm.application.service.person;

import com.finnect.crm.application.port.in.person.FindPeopleUsecase;
import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.application.port.out.person.LoadPeoplePort;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithCompany;
import com.finnect.crm.domain.person.PersonWithCompanyState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
class FindPersonService implements FindPeopleUsecase {

    private final LoadPeoplePort loadPeoplePort;
    private final LoadCompanyPort loadCompanyPort;

    @Override
    public List<PersonState> findPeopleByCompany(Long companyId) {
        //TODO: Company가 존재하는지 검사

        return loadPeoplePort.loadPeopleByCompanyId(companyId);
    }

    @Override
    public List<PersonWithCompanyState> findPeopleByWorkspace(Long workspaceId) {
        //TODO: Workspace가 존재하는지 검사

        List<PersonState> people = loadPeoplePort.loadPeopleByWorkspaceId(workspaceId);
        Map<Long, String> companiesMap = loadCompanyPort.loadCompaniesByWorkspaceId(workspaceId)
                .stream()
                .collect(Collectors.toMap(CompanyState::getCompanyId, CompanyState::getCompanyName));

        List<PersonWithCompany> peopleWithCompany = people
                .stream()
                .map(p -> PersonWithCompany.of(p, companiesMap.get(p.getCompanyId())))
                .toList();

        return new ArrayList<>(peopleWithCompany);
    }
}
