package com.finnect.crm.application.port.out.deal;

import com.finnect.crm.domain.deal.state.DealState;
import com.finnect.crm.domain.deal.Deal;

public interface SaveDealPort {

    Deal saveDeal(DealState deal);
}
