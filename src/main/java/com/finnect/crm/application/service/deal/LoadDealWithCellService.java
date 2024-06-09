package com.finnect.crm.application.service.deal;

import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.in.deal.LoadDealWithCellUseCase;
import com.finnect.crm.application.port.in.person.FindPeopleUsecase;
import com.finnect.crm.application.port.out.column.LoadColumnCountPort;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellDSLPort;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellPort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.cell.state.DataCellState;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.deal.DealCell;
import com.finnect.crm.domain.deal.DealCellDetail;
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

    private final LoadDealWithCellDSLPort loadDealWithCellDSLPort;
    @Override
    public List<DealCell> loadDealWithCell(Long workspaceId, List<FilterState> filters, Integer page) {
        int columnCount = loadColumnCountPort.loadDealColumnCount(workspaceId);
        List<DealCell> dealCells = loadDealWithCellPort.loadDealsWithCellsByFilter(filters, page, columnCount);
        List<DataCellState> dataCellStates = dealCells.stream()
                .flatMap(dealCell -> dealCell.getDataCellStates().stream())
                .toList();
        setPersonInfo(workspaceId, dataCellStates);
        setCompanyInfo(workspaceId, dataCellStates);
        setMemberInfo(workspaceId, dataCellStates);
        return dealCells;
    }

    @Override
    public DealCellDetail loadDealWithCellDetail(Long workspaceId, Long dealId) {
        DealCellDetail dealCellDetail = loadDealWithCellDSLPort.queryDSLTest(dealId);
        setPersonInfo(workspaceId, dealCellDetail.getDataCells());
        setCompanyInfo(workspaceId, dealCellDetail.getDataCells());
        setMemberInfo(workspaceId, dealCellDetail.getDataCells());
        return dealCellDetail;
    }

    private void setPersonInfo(Long workspaceId, List<DataCellState> dataCellStates){
        var info = findPeopleUsecase.findPeopleByWorkspace(workspaceId);
        log.info(info.toString());
        var personInfos = info
                .stream()
                .collect(Collectors.toMap(
                        PersonWithCompanyState::getPersonId,
                        person -> person
                ));

        dataCellStates.forEach( dataCellState -> {
            if (dataCellState.getPeopleId() != null) {
                ((DataCell) dataCellState).setValue(
                        personInfos.get(dataCellState.getPeopleId()).getPersonName());
            }
        });
    }

    private void setCompanyInfo(Long workspaceId, List<DataCellState> dataCellStates){

        var info = loadCompanyUseCase.loadCompaniesByWorkspaceId(workspaceId);
        log.info(info.toString());
        var companyInfos = info
                .stream()
                .collect(Collectors.toMap(
                        CompanyState::getCompanyId,
                        companyState -> companyState
                ));
        dataCellStates.forEach(dataCellState -> {
            if (dataCellState.getCompanyId() != null) {
                ((DataCell) dataCellState).setValue(companyInfos.get(dataCellState.getCompanyId()).getCompanyName());
            }
        });
    }

    private void setMemberInfo(Long workspaceId, List<DataCellState> dataCellStates){
        var info = findMembersUsecase.loadMembersByWorkspace(workspaceId);
        var memberInfos = info
                .stream()
                .collect(Collectors.toMap(
                        MemberState::getUserId,
                        memberState -> memberState
                ));
        dataCellStates.forEach(dataCellState -> {
            if (dataCellState.getUserId() != null) {
                ((DataCell) dataCellState).setValue(memberInfos.get(dataCellState.getUserId()).getNickname());
            }
        });
    }

    private void setDealManagerInfo(Long workspaceId, List<DealCell> dealCells){

        var info = findMembersUsecase.loadMembersByWorkspace(workspaceId);
        var memberInfos = info
                .stream()
                .collect(Collectors.toMap(
                        MemberState::getUserId,
                        memberState -> memberState
                ));
        dealCells.
        forEach(dealCell -> {
            dealCell.setUserName(memberInfos.get(dealCell.getUserId()).getNickname());
        });
    }

}
