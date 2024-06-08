package com.finnect.crm.application.service.company;

import com.finnect.crm.adapter.out.persistence.cell.CellId;
import com.finnect.crm.application.port.in.company.LoadCompanyUseCase;
import com.finnect.crm.application.port.out.cell.LoadDataCellPort;
import com.finnect.crm.application.port.out.column.LoadDataColumnPort;
import com.finnect.crm.application.port.out.company.LoadCompanyPort;
import com.finnect.crm.application.port.out.person.LoadPeoplePort;
import com.finnect.crm.domain.cell.DataCell;
import com.finnect.crm.domain.column.ColumnType;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.state.DataColumnState;
import com.finnect.crm.domain.company.CompanyDetail;
import com.finnect.crm.domain.company.CompanyState;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.finnect.crm.domain.person.PersonState;
import com.finnect.workspace.application.port.out.FindMembersPort;
import com.finnect.workspace.domain.state.MemberState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoadCompanyService implements LoadCompanyUseCase {
    private final LoadCompanyPort loadCompanyPort;
    private final LoadDataColumnPort loadDataColumnPort;
    private final LoadDataCellPort loadDataCellPort;
    private final FindMembersPort findMembersPort;
    private final LoadPeoplePort loadPeoplePort;

    @Override
    public List<CompanyState> loadCompaniesByWorkspaceId(Long workspaceId) {

        return loadCompanyPort.loadCompaniesByWorkspaceId(workspaceId);
    }

    @Override
    public CompanyDetail loadCompanyDetail(Long companyId) {
        CompanyState loadedState = loadCompanyPort.loadById(companyId);

        List<DataColumn> columnStates = loadDataColumnPort.loadDataColumnsOfCompany(loadedState.getWorkspaceId());

        Map<Long, String> columnNameMap = columnStates.stream()
                .collect(Collectors.toMap(DataColumnState::getColumnId, DataColumnState::getColumnName));
        Map<Long, ColumnType> columnTypeMap = columnStates.stream()
                .collect(Collectors.toMap(DataColumnState::getColumnId, DataColumnState::getColumnType));

        Map<Long, String> memberNameMap = findMembersPort.findMembersByWorkspace(loadedState.getWorkspaceId())
                .stream()
                .collect(Collectors.toMap(
                        MemberState::getUserId,
                        MemberState::getNickname
                ));
        Map<Long, String> peopleNameMap = loadPeoplePort.loadPeopleByWorkspaceId(loadedState.getWorkspaceId())
                .stream()
                .collect(Collectors.toMap(
                        PersonState::getPersonId,
                        PersonState::getPersonName
                ));

        List<DataCell> cells = loadDataCellPort.loadDataCellsByRowId(
                        DataCell.builder()
                                .cellId(new CellId(loadedState.getDataRowId(), null))
                                .build()
                );

        for (DataCell cell : cells) {
            if (cell.getUserId() != null)
                cell.setValue(memberNameMap.get(cell.getUserId()));
            if (cell.getPeopleId() != null) {
                cell.setValue(peopleNameMap.get(cell.getPeopleId()));
            }
        }

        return CompanyDetail.of(
                loadedState,
                cells,
                columnNameMap,
                columnTypeMap
        );
    }
}
