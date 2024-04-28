package com.finnect.deal.application.service;

import com.finnect.deal.application.DealState;
import com.finnect.deal.application.port.in.LoadDealUseCase;
import com.finnect.deal.application.port.out.LoadDealPort;
import com.finnect.deal.domain.Deal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadDealService implements LoadDealUseCase {

    private final LoadDealPort loadDealPort;
    @Override
    public DealState loadDeal(Deal deal) {
        deal = loadDealPort.findDealById(deal);
        return deal;
    }
}
