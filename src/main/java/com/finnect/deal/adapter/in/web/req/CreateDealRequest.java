package com.finnect.deal.adapter.in.web.req;

public class CreateDealRequest {
    private String dealName;
    private Long companyId;
    private Long userId;
    private Long workspaceId;

    public CreateDealRequest(String dealName, Long companyId, Long userId, Long workspaceId) {
        this.dealName = dealName;
        this.companyId = companyId;
        this.userId = userId;
        this.workspaceId = workspaceId;
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

    public Long getWorkspaceId() {
        return workspaceId;
    }
}
