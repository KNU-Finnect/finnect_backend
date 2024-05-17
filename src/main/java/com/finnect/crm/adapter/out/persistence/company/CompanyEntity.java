package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.adapter.out.persistence.cell.DataRowEntity;
import com.finnect.crm.domain.company.CompanyState;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
class CompanyEntity implements CompanyState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private Long workspaceId;

    @OneToOne(fetch = FetchType.EAGER)
    private DataRowEntity dataRowEntity;

    private String domain;

    private String companyName;

    @Override
    public Long getDataRowId() {
        return this.dataRowEntity.getDataRowId();
    }
}
