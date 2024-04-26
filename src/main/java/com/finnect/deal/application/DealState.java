package com.finnect.deal.application;

import com.finnect.mockDomain.MemberEntity;

public interface DealState {

    Long getDealId();
    String getDealName();
    Long getCompany();
    Long getUser();
    Long getWorkspace();
    Long getDataRow();

}
