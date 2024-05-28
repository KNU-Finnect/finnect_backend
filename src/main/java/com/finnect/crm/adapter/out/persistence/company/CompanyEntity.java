package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.domain.company.CompanyState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class CompanyEntity implements CompanyState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private Long workspaceId;

    private Long dataRowId;

    private String domain;

    private String companyName;

    public static CompanyEntity from(CompanyState state) {
        return new CompanyEntity(
                state.getWorkspaceId(),
                state.getWorkspaceId(),
                state.getDataRowId(),
                state.getDomain(),
                state.getCompanyName()
        );
    }

    @Override
    public Long getDataRowId() {
        return this.dataRowId;
    }
}
