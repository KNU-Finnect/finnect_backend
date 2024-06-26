package com.finnect.crm.application.service.company;

import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.in.company.LoadCompanyWithCellUseCase;
import com.finnect.crm.application.port.in.person.FindPeopleUsecase;
import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.company.LoadCompanyWithCellPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.person.PersonWithCompanyState;
import com.finnect.view.domain.state.FilterState;
import com.finnect.workspace.application.port.in.FindMembersUsecase;
import com.finnect.workspace.domain.state.MemberState;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadCompanyWithCellUseService implements LoadCompanyWithCellUseCase {
    private final LoadCompanyWithCellPort loadCompanyWithCellPort;
    private final LoadColumnCountPort loadColumnCountPort;
    private final FindPeopleUsecase findPeopleUsecase;
    private final LoadCompanyUseCase loadCompanyUseCase;
    private final FindMembersUsecase findMembersUsecase;
    @Override
    public List<CompanyCell> loadCompanyWithCell(Long workspaceId, List<FilterState> filters, Integer page) {
        // Column 개수 반환
        var columnCnt = loadColumnCountPort.loadCompanyColumnCount(workspaceId);

        var companyCells = loadCompanyWithCellPort.loadCompaniesWithCellsByFilter(workspaceId,
                filters, page, columnCnt);

        setPersonInfo(workspaceId, companyCells);
        setCompanyInfo(workspaceId, companyCells);
        setMemberInfo(workspaceId, companyCells);

        return companyCells;
    }

    private void setPersonInfo(Long workspaceId, List<CompanyCell> companyCells){
        var info = findPeopleUsecase.findPeopleByWorkspace(workspaceId);
        log.info(info.toString());
        var personInfos = info
                .stream()
                .collect(Collectors.toMap(
                        PersonWithCompanyState::getPersonId,
                        person -> person
                ));

        companyCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getPeopleId() != null){
                        ((DataCell) dataCellState).setValue(personInfos.get(dataCellState.getPeopleId()).getPersonName());
                    }
                });
    }

    private void setCompanyInfo(Long workspaceId, List<CompanyCell> companyCells){

        var info = loadCompanyUseCase.loadCompaniesByWorkspaceId(workspaceId);
        log.info(info.toString());
        var companyInfos = info
                .stream()
                .collect(Collectors.toMap(
                        CompanyState::getCompanyId,
                        companyState -> companyState
                ));

        companyCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getCompanyId() != null){
                        ((DataCell) dataCellState).setValue(companyInfos.get(dataCellState.getCompanyId()).getCompanyName());
                    }
                });
    }

    private void setMemberInfo(Long workspaceId, List<CompanyCell> companyCells){

        var info = findMembersUsecase.loadMembersByWorkspace(workspaceId);
        log.info(info.toString());
        var memberInfos = info
                .stream()
                .collect(Collectors.toMap(
                        MemberState::getUserId,
                        memberState -> memberState
                ));
        log.info(memberInfos.toString());
        companyCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getUserId() != null){
                        ((DataCell) dataCellState).setValue(memberInfos.get(dataCellState.getUserId()).getNickname());
                    }
                });
    }
}
