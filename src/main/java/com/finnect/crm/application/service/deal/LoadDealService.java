package com.finnect.crm.application.service.deal;

import com.finnect.crm.domain.deal.state.DealState;
import com.finnect.crm.application.port.in.deal.LoadDealUseCase;
import com.finnect.crm.application.port.out.deal.LoadDealPort;
import com.finnect.crm.domain.deal.Deal;
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
