package com.finnect.deal.application.port.out;

import com.finnect.deal.application.DealState;
import com.finnect.deal.domain.Deal;

public interface LoadDealPort {

    Deal findDealById(DealState dealState);
}
