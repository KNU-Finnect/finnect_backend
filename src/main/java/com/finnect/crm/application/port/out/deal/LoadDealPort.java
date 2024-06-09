package com.finnect.crm.application.port.out.deal;

import com.finnect.crm.domain.deal.Deal;
import com.finnect.crm.domain.deal.state.DealState;

public interface LoadDealPort {

    Deal findDealById(DealState dealState);


    Deal findDealByRowId(Long rowId);
}
