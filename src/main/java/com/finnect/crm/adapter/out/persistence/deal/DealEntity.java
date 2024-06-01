package com.finnect.crm.adapter.out.persistence.deal;

import com.finnect.crm.domain.deal.state.DealState;
import com.finnect.crm.domain.deal.Deal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;


@Entity(name = "deal")
public class DealEntity implements DealState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    private String dealName;

    private Long companyId;

    private Long userId;
    private Long workspaceId;

    private Long dataRowId;

    protected DealEntity() {}


    @Builder
    public DealEntity(Long dealId, String dealName, Long companyId, Long userId, Long workspaceId, Long dataRowId) {
        this.dealId = dealId;
        this.dealName = dealName;
        this.companyId = companyId;
        this.userId = userId;
        this.workspaceId = workspaceId;
        this.dataRowId = dataRowId;
    }


    @Override
    public Long getDealId() {
        return this.dealId;
    }

    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public Long getWorkspaceId() {
        return this.workspaceId;
    }

    @Override
    public Long getDataRowId() {
        return this.dataRowId;
    }

    @Override
    public String getDealName() {
        return this.dealName;
    }



    public Deal toDomain(){
        return Deal.builder()
                .dealId(this.getDealId())
                .dealName(this.getDealName())
                .companyId(this.getCompanyId())
                .userId(this.getUserId())
                .workspaceId(this.getWorkspaceId())
                .dataRowId(this.getDataRowId())
                .build();
    }

    public static DealEntity toPersistence(final DealState deal){
        return DealEntity.builder()
                .dealId(deal.getDealId())
                .dealName(deal.getDealName())
                .userId(deal.getUserId())
                .workspaceId(deal.getWorkspaceId())
                .dataRowId(deal.getDataRowId())
                .companyId(deal.getCompanyId())
                .build();
    }

    @Override
    public String toString() {
        return "DealEntity{" +
                "dealId=" + dealId +
                ", dealName='" + dealName + '\'' +
                ", companyId=" + companyId +
                ", userId=" + userId +
                ", workspaceId=" + workspaceId +
                ", dataRowId=" + dataRowId +
                '}';
    }
}
