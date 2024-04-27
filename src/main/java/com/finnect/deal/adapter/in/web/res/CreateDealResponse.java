package com.finnect.deal.adapter.in.web.res;

import com.finnect.deal.application.DealState;
import lombok.Builder;


public class CreateDealResponse implements DealState {
    private Long dealId;
    private String dealName;
    private Long companyId;
    private Long userId;
    private Long workspaceId;
    private Long dataRowId;
    @Builder
    public CreateDealResponse(Long dealId, String dealName, Long companyId, Long userId, Long workspaceId, Long dataRowId) {
        this.dealId = dealId;
        this.dealName = dealName;
        this.companyId = companyId;
        this.userId = userId;
        this.workspaceId = workspaceId;
        this.dataRowId = dataRowId;
    }

    public static CreateDealResponse toDTO(DealState deal){
        return CreateDealResponse.builder()
                .dealId(deal.getDealId())
                .dealName(deal.getDealName())
                .companyId(deal.getCompanyId())
                .userId(deal.getUserId())
                .workspaceId(deal.getWorkspaceId())
                .dataRowId(deal.getDataRowId())
                .build();
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
    public String toString() {
        return "CreateDealDTO{" +
                "dealId=" + dealId +
                ", dealName='" + dealName + '\'' +
                ", companyId=" + companyId +
                ", userId=" + userId +
                ", workspaceId=" + workspaceId +
                ", dataRowId=" + dataRowId +
                '}';
    }

}
