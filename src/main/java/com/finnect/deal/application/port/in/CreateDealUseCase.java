package com.finnect.deal.application.port.in;

import com.finnect.deal.application.DealState;
import com.finnect.deal.domain.Deal;

public interface CreateDealUseCase {
    DealState createDeal(Deal deal);
}
