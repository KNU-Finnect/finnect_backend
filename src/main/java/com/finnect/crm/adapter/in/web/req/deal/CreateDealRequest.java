package com.finnect.crm.adapter.in.web.req.deal;

import com.finnect.crm.domain.deal.Deal;
import lombok.Getter;
import lombok.ToString;

@ToString
public class CreateDealRequest {
    private String dealName;
    private Long companyId;

    public CreateDealRequest(String dealName, Long companyId, Long userId) {
        this.dealName = dealName;
        this.companyId = companyId;
    }

    public String getDealName() {
        return dealName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Deal toDomain(Long workspaceId, Long userId){
        return Deal.builder()
                .workspaceId(workspaceId)
                .companyId(this.getCompanyId())
                .dealName(this.getDealName())
                .userId(userId)
                .build();
    }
}
