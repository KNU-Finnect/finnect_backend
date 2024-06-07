package com.finnect.crm.application.service.company;

import com.finnect.common.error.CustomException;
import com.finnect.crm.application.port.in.cell.CreateNewRowUseCase;
import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.application.port.out.company.SearchCompanyPort;
import com.finnect.crm.domain.cell.state.DataRowState;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.company.CompanyWithoutId;
import com.finnect.workspace.application.port.out.CheckQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class CreateCompanyService implements CreateCompanyUsecase {

    private final SearchCompanyPort searchCompanyPort;
    private final CreateNewRowUseCase createNewRowUseCase;
    private final SaveCompanyPort saveCompanyPort;
    private final CheckQuery checkQuery;

    @Override
    public CompanyState createCompany(CreateCompanyCommand cmd) {
        if (searchCompanyPort.searchByDomain(cmd.getDomain()))
            throw new RuntimeException(cmd.getDomain() + " 도메인이 이미 존재합니다.");
        if (checkQuery.checkExistWorkspace(cmd.getWorkspaceId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, cmd.getWorkspaceId() + " 워크스페이스가 존재하지 않습니다.");

        DataRowState dataRow = createNewRowUseCase.createNewDataRow(
                DataColumn.builder()
                        .workspaceId(cmd.getWorkspaceId())
                        .build()
        );

        CompanyWithoutId companyWithoutId = CompanyWithoutId.builder()
                .domain(cmd.getDomain())
                .companyName(cmd.getCompanyName())
                .workspaceId(cmd.getWorkspaceId())
                .dataRowId(dataRow.getDataRowId())
                .build();

        return saveCompanyPort.save(companyWithoutId);
    }
}
