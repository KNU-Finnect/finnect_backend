package com.finnect.crm.adapter.in.web.req.deal;

import com.finnect.crm.domain.deal.Deal;

public class CreateDealRequest {
    private String dealName;
    private Long companyId;
    private Long userId;

    public CreateDealRequest(String dealName, Long companyId, Long userId) {
        this.dealName = dealName;
        this.companyId = companyId;
        this.userId = userId;
    }

    public String getDealName() {
        return dealName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getUserId() {
        return userId;
    }
    public Deal toDomain(Long workspaceId){
        return Deal.builder()
                .workspaceId(workspaceId)
                .companyId(this.getCompanyId())
                .dealName(this.getDealName())
                .userId(this.getUserId())
                .build();
    }
}
