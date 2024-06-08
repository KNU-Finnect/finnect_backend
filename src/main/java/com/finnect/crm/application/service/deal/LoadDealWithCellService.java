package com.finnect.crm.application.service.deal;

import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.in.deal.LoadDealWithCellUseCase;
import com.finnect.crm.application.port.in.person.FindPeopleUsecase;
import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.deal.DealCell;
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
@Slf4j
@RequiredArgsConstructor
public class LoadDealWithCellService implements LoadDealWithCellUseCase {
    private final LoadDealWithCellPort loadDealWithCellPort;
    private final LoadColumnCountPort loadColumnCountPort;

    private final FindPeopleUsecase findPeopleUsecase;
    private final LoadCompanyUseCase loadCompanyUseCase;
    private final FindMembersUsecase findMembersUsecase;
    @Override
    public List<DealCell> loadDealWithCell(Long workspaceId, List<FilterState> filters, Integer page) {
        int columnCount = loadColumnCountPort.loadDealColumnCount(workspaceId);
        List<DealCell> dealCells = loadDealWithCellPort.loadDealsWithCellsByFilter(filters, page, columnCount);

        setPersonInfo(workspaceId, dealCells);
        setCompanyInfo(workspaceId, dealCells);
        setMemberInfo(workspaceId, dealCells);
        return dealCells;
    }
    private void setPersonInfo(Long workspaceId, List<DealCell> dealCells){
        var info = findPeopleUsecase.findPeopleByWorkspace(workspaceId);
        log.info(info.toString());
        var personInfos = info
                .stream()
                .collect(Collectors.toMap(
                        PersonWithCompanyState::getPersonId,
                        person -> person
                ));

        dealCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getPeopleId() != null){
                        ((DataCell) dataCellState).setValue(
                                personInfos.get(dataCellState.getPeopleId()).getPersonName());
                    }
                });
    }

    private void setCompanyInfo(Long workspaceId, List<DealCell> dealCells){

        var info = loadCompanyUseCase.loadCompaniesByWorkspaceId(workspaceId);
        log.info(info.toString());
        var companyInfos = info
                .stream()
                .collect(Collectors.toMap(
                        CompanyState::getCompanyId,
                        companyState -> companyState
                ));

        dealCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getCompanyId() != null){
                        ((DataCell) dataCellState).setValue(companyInfos.get(dataCellState.getCompanyId()).getCompanyName());
                    }
                });
    }

    private void setMemberInfo(Long workspaceId, List<DealCell> dealCells){

        var info = findMembersUsecase.loadMembersByWorkspace(workspaceId);
        log.info(info.toString());
        var memberInfos = info
                .stream()
                .collect(Collectors.toMap(
                        MemberState::getUserId,
                        memberState -> memberState
                ));
        log.info(memberInfos.toString());
        dealCells.stream()
                .flatMap(companyCell -> companyCell.getDataCellStates().stream()) // 각 companyCell의 dataCellStates를 평면화
                .forEach(dataCellState -> {
                    if(dataCellState.getUserId() != null){
                        ((DataCell) dataCellState).setValue(memberInfos.get(dataCellState.getUserId()).getNickname());
                    }
                });
    }
}
