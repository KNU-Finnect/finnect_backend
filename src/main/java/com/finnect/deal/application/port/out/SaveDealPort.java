package com.finnect.deal.application.port.out;

import com.finnect.deal.application.DealState;

public interface SaveDealPort {

    void saveDeal(DealState deal);
}
