package com.finnect.deal.adapter.out.persistence;

import com.finnect.cell.adapter.out.persistence.DataRowEntity;
import com.finnect.company.adapter.out.persistence.entity.CompanyEntity;
import com.finnect.deal.application.DealState;
import com.finnect.deal.domain.Deal;
import com.finnect.mockDomain.MemberEntity;
import com.finnect.mockDomain.MemberId;
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


@Entity(name = "deal")
public class DealEntity implements DealState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    private String dealName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id"),
            @JoinColumn(name = "workspace_id")
    })

    private MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_row_id")
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
    public Long getDealId() {
        return this.dealId;
    }

    @Override
    public Long getCompany() {
        return this.companyEntity.getCompanyId();
    }

    @Override
    public Long getUser() {
        return this.memberEntity.getUserId();
    }

    @Override
    public Long getWorkspace() {
        return this.memberEntity.getWorkspaceId();
    }

    @Override
    public Long getDataRow() {
        return this.dataRowEntity.getDataRowId();
    }

    @Override
    public String getDealName() {
        return this.dealName;
    }



    public Deal toDomain(){
        return Deal.builder()
                .dealId(this.getDealId())
                .dealName(this.getDealName())
                .companyId(this.getCompany())
                .userId(this.getUser())
                .workspaceId(this.getWorkspace())
                .dataRowId(this.getDataRow())
                .build();
    }

    public static DealEntity toPersistence(final DealState deal){
        return DealEntity.builder()
                .dealId(deal.getDealId())
                .dealName(deal.getDealName())
                .memberEntity(null)
                .dataRowEntity(new DataRowEntity(deal.getDataRow()))
                .build();
    }

    @Override
    public String toString() {
        return "DealEntity{" +
                "dealId=" + dealId +
                ", dealName='" + dealName + '\'' +
                ", companyEntity=" + companyEntity +
                ", memberEntity=" + memberEntity +
                ", dataRowEntity=" + dataRowEntity +
                '}';
    }
}
