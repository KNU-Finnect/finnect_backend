package com.finnect.deal.domain;

import com.finnect.deal.application.DealState;
import lombok.Builder;
import lombok.Setter;

public class Deal implements DealState {


    private Long dealId;
    private String dealName;
    private Long companyId;
    private Long userId;
    private Long workspaceId;
    @Setter
    private Long dataRowId;

    @Builder
    public Deal(Long dealId, String dealName, Long companyId, Long userId, Long workspaceId, Long dataRowId) {
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
    public String getDealName() {
        return this.dealName;
    }

    @Override
    public Long getCompany() {
        return this.companyId;
    }

    @Override
    public Long getUser() {
        return this.userId;
    }

    @Override
    public Long getWorkspace() {
        return this.workspaceId;
    }

    @Override
    public Long getDataRow() {
        return this.dataRowId;
    }

    public void changeDealName(final String dealName){
        this.dealName = dealName;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "dealId=" + dealId +
                ", dealName='" + dealName + '\'' +
                ", companyId=" + companyId +
                ", userId=" + userId +
                ", workspaceId=" + workspaceId +
                ", dataRowId=" + dataRowId +
                '}';
    }
}
