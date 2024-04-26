package com.finnect.deal.adapter.out.persistence;

import com.finnect.company.adapter.out.persistence.entity.CompanyEntity;
import com.finnect.deal.application.DealState;
import com.finnect.mockDomain.DataRowEntity;
import com.finnect.mockDomain.MemberEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;


@Entity(name = "deal")
public class DealEntity implements DealState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    private String dealName;

    // Identified By Company
    // Mock Entity
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    // Identified By userId & workspaceId
    // Mock Entity
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id"),
            @JoinColumn(name = "workspace_id")
    })
    @Getter
    private MemberEntity memberEntity;
    // Identified By rowId
    // Mock Entity

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_row_id")
    @Getter
    private DataRowEntity dataRowEntity;

    protected DealEntity() {}
    @Builder
    public DealEntity(Long dealId, String dealName, CompanyEntity companyEntity, MemberEntity memberEntity, DataRowEntity dataRowEntity) {
        this.dealId = dealId;
        this.dealName = dealName;
        this.companyEntity = companyEntity;
        this.memberEntity = memberEntity;
        this.dataRowEntity = dataRowEntity;
    }

    @Override
    public String getCompany() {
        return String.valueOf(this.companyEntity.getCompanyId());
    }

    @Override
    public String getDealName() {
        return null;
    }

    @Override
    public MemberEntity getMemberInCharge() {
        return null;
    }

}
