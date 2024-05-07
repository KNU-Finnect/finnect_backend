package com.finnect.crm.adapter.out.deal;

import com.finnect.crm.domain.deal.state.DealState;
import com.finnect.crm.application.port.out.deal.LoadDealPort;
import com.finnect.crm.application.port.out.deal.SaveDealPort;
import com.finnect.crm.domain.deal.Deal;
import com.finnect.crm.adapter.out.deal.persistence.DealEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DealPersistenceAdapter implements SaveDealPort, LoadDealPort {

    private final DealRepository dealRepository;


    @Override
    public Deal saveDeal(DealState deal) {
        DealEntity dealEntity = DealEntity.toPersistence(deal);
        log.info(dealEntity.toString());
        dealEntity = dealRepository.save(dealEntity);

        log.info("saved : " + dealEntity.toString());
        return dealEntity.toDomain();
    }

    @Override
    public Deal findDealById(DealState dealState) {
        DealEntity dealEntity = dealRepository.findById(dealState.getDealId())
                .orElseThrow(IllegalArgumentException::new);
        return dealEntity.toDomain();
    }
}
