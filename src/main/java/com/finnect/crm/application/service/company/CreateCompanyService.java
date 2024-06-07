package com.finnect.crm.application.service.company;

import com.finnect.common.error.CustomException;
import com.finnect.crm.application.port.in.cell.CreateNewRowUseCase;
import com.finnect.crm.application.port.in.company.CreateCompanyCommand;
import com.finnect.crm.application.port.in.company.CreateCompanyUsecase;
import com.finnect.crm.application.port.out.company.CheckCompanyQuery;
import com.finnect.crm.application.port.out.company.SaveCompanyPort;
import com.finnect.crm.domain.cell.state.DataRowState;
import com.finnect.crm.domain.column.DataColumn;
import com.finnect.crm.domain.column.DataType;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.company.CompanyWithoutId;
import com.finnect.workspace.application.port.out.CheckWorkspaceQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class CreateCompanyService implements CreateCompanyUsecase {

    private final CreateNewRowUseCase createNewRowUseCase;
    private final SaveCompanyPort saveCompanyPort;
    private final CheckWorkspaceQuery checkWorkspaceQuery;
    private final CheckCompanyQuery checkCompanyQuery;

    @Override
    public CompanyState createCompany(CreateCompanyCommand cmd) {
        if (checkCompanyQuery.checkDomainExists(cmd.getDomain()))
            throw new RuntimeException(cmd.getDomain() + " 도메인이 이미 존재합니다.");
        if (!checkWorkspaceQuery.checkWorkspaceExists(cmd.getWorkspaceId()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ID " + cmd.getWorkspaceId() + "은/는 존재하지 않는 워크스페이스입니다.");

        DataRowState dataRow = createNewRowUseCase.createNewDataRow(
                DataColumn.builder()
                        .workspaceId(cmd.getWorkspaceId())
                        .dType(DataType.COMPANY)
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
