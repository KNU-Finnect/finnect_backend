package com.finnect.deal.application;

import com.finnect.mockDomain.MemberEntity;

public interface DealState {

    String getCompany();
    String getDealName();
    MemberEntity getMemberInCharge();
}
