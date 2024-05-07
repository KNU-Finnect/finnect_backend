package com.finnect.crm.application.port.in.deal;

import com.finnect.crm.domain.deal.Deal;
import com.finnect.crm.domain.deal.state.DealState;

public interface LoadDealUseCase {

    DealState loadDeal(Deal deal);
}
